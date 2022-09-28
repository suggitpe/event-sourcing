package org.suggs.sandbox.eventsourcing.bankaccount.accounts.aggregate

import org.slf4j.LoggerFactory
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.Customer
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command.CreateNewAccount
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command.CreateNewCustomer
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.CustomerCreated
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.CustomerRejected
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.Event
import java.time.LocalDate
import java.util.*

class CustomerAggregate : Aggregate<CreateNewCustomer> {

    private var customers: Map<UUID, Customer> = mapOf()

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    override fun execute(command: CreateNewCustomer): Event {
        return when {
            customerExists(command.firstName, command.lastName, command.dateOfBirth) -> {
                log.info("Customer already exists for [$command]")
                CustomerRejected(command.requestId, "Customer already exists")
            }
            else -> {
                CustomerCreated(command.requestId, createCustomer(command))
            }
        }
    }

    private fun customerExists(firstName: String, lastName: String, dateOfBirth: LocalDate) =
        customers.values.any { it.firstName == firstName && it.lastName == lastName && it.dateOfBirth == dateOfBirth }

    private fun createCustomer(command: CreateNewCustomer): Customer {
        val customerId = UUID.randomUUID()
        // do some checks etc
        log.debug("Creating new customer from $command")
        return Customer(customerId, command.firstName, command.lastName, command.dateOfBirth)
    }

    override fun observeEvent(event: Event) {
        when (event) {
            is CustomerCreated -> customers = customers + mapOf(event.customer.customerId to event.customer)
        }
    }
}