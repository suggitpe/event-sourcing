package org.suggs.sandbox.eventsourcing.bankaccount.accounts.projection

import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.Customer
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.CustomerCreated
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.Event
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.eventstore.EventStore
import java.time.LocalDate
import java.util.*
import javax.inject.Inject

class InMemoryCustomerProjection : CustomerReadProjection {

    private var customers: Map<UUID, Customer> = mapOf()

    @Inject
    private lateinit var eventRepository: EventStore

    override fun customerExists(firstName: String, lastName: String, dateOfBirth: LocalDate): Boolean {
        return false
    }

    override fun processEvent(event: Event) {
        if (event is CustomerCreated) {
            customers = customers + mapOf(event.customer.customerId to event.customer)
        }
    }
}