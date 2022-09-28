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
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command.CreateNewAccount
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.AccountCreated
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.eventstore.EventStore
import java.math.BigDecimal
import java.util.*
import javax.inject.Inject

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [CommandHandlerConfig::class])
class CreateNewAccountTest {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
        private val requestId = UUID.randomUUID()
        private val initialBalance = BigDecimal.valueOf(100.0)
    }

    @Inject
    private lateinit var commandBus: CommandBus

    @Inject
    private lateinit var eventStore: EventStore

    @BeforeEach
    fun `clear event repo`() {
        eventStore.clear()
    }

    @Test
    fun `creates a new account when requested`() {
        commandBus.publish(CreateNewAccount(requestId, UUID.randomUUID(), initialBalance))
        eventStore.size() shouldBe 1
        eventStore.read().shouldBeInstanceOf<AccountCreated>()
    }

}