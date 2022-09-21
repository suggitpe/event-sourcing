package org.suggs.sandbox.eventsourcing.bankaccount.accounts

import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.slf4j.LoggerFactory
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandbus.CommandBus
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandhandler.CommandHandlerConfig
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command.CreateNewCustomer
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.ActivityConfirmedToClient
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.CustomerCreated
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.eventstore.EventStore
import java.time.LocalDate
import java.util.*
import javax.inject.Inject

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [CommandHandlerConfig::class])
class CreateNewCustomerTest {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
        private val requestId = UUID.randomUUID()
    }

    @Inject
    private lateinit var commandBus: CommandBus

    @Inject
    private lateinit var eventRepo: EventStore

    @BeforeEach
    fun `clear event repo`() {
        eventRepo.clear()
    }

    @Test
    fun `creates a new customer when requested`() {
        commandBus.publish(CreateNewCustomer(requestId, "Joe", "Bloggs", LocalDate.of(1979, 11, 23)))
        eventRepo.size() shouldBe 2
        eventRepo.read().shouldBeInstanceOf<CustomerCreated>()
        eventRepo.read().shouldBeInstanceOf<ActivityConfirmedToClient>()
    }
}