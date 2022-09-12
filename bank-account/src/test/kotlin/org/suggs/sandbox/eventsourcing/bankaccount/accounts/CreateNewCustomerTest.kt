package org.suggs.sandbox.eventsourcing.bankaccount.accounts

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.slf4j.LoggerFactory
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.bus.CommandBus
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandhandler.CommandHandlerConfig
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command.CreateNewCustomer
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.repository.EventRepository
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
    private lateinit var eventRepo: EventRepository

    @BeforeEach
    fun `clear event repo`() {
        eventRepo.clear()
    }

    @Test
    fun `creates a new customer when requested`() {
        commandBus.publish(CreateNewCustomer(requestId, "Joe", "Bloggs", LocalDate.of(1979, 11, 23)))
        eventRepo.size() shouldBe 2
    }
}