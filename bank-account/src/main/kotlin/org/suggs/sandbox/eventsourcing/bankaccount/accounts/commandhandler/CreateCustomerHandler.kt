package org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandhandler

import com.google.common.eventbus.AllowConcurrentEvents
import com.google.common.eventbus.Subscribe
import org.slf4j.LoggerFactory
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.bus.CommandBus
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.Customer
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command.ConfirmActivityToClient
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command.CreateNewCustomer
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.CustomerCreated
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.repository.EventRepository
import java.util.*

class CreateCustomerHandler(private val eventRepo: EventRepository, private val commandBus: CommandBus) : CommandHandler<CreateNewCustomer> {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    @Subscribe
    @AllowConcurrentEvents
    override fun handle(command: CreateNewCustomer) {
        val event = CustomerCreated(command.requestId, createNewCustomer(command))
        eventRepo.save(event)
        commandBus.publish(ConfirmActivityToClient(command.requestId, event))
    }

    private fun createNewCustomer(command: CreateNewCustomer): Customer {
        val customerId = UUID.randomUUID()
        // do some checks etc
        log.debug("Creating new customer from $command")
        return Customer(customerId, command.firstName, command.lastName, command.dateOfBirth)
    }
}