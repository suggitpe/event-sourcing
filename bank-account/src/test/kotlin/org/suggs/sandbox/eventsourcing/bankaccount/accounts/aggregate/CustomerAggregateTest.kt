package org.suggs.sandbox.eventsourcing.bankaccount.accounts.aggregate

import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.Customer
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command.CreateNewCustomer
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.CustomerCreated
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.CustomerRejected
import java.time.LocalDate
import java.util.UUID


class CustomerAggregateTest {

    private val requestId: UUID = UUID.randomUUID()
    private val command = CreateNewCustomer(requestId, "Joe", "Bloggs", LocalDate.of(1900, 1, 1))
    private lateinit var customer: Aggregate<CreateNewCustomer>


    @BeforeEach
    fun initialise(){
        customer = CustomerAggregate()
    }


    @Test
    fun `creates customers when customer does not exist`(){
        customer.execute(command).shouldBeInstanceOf<CustomerCreated>()
    }

    @Test
    fun `rejects new customer when customer already exists`(){
        customer.observeEvent(CustomerCreated(command.requestId, Customer(UUID.randomUUID(), command.firstName, command.lastName, command.dateOfBirth)))
        customer.execute(command).shouldBeInstanceOf<CustomerRejected>()
    }
}