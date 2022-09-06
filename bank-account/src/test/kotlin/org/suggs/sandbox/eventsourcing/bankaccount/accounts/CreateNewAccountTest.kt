package org.suggs.sandbox.eventsourcing.bankaccount.accounts

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.slf4j.LoggerFactory
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.bus.CommandBus
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandhandler.CommandHandlerConfiguration
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.commands.CreateAccountCommand
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.repository.AccountRepository
import java.math.BigDecimal
import java.util.*
import javax.inject.Inject

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [CommandHandlerConfiguration::class])
class CreateNewAccountTest {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
        private val requestId = UUID.randomUUID()
        private val initialBalance = BigDecimal.valueOf(100.0)
    }

    @Inject
    lateinit var commandBus: CommandBus

    @Inject
    lateinit var accountRepository: AccountRepository

    @Test
    fun createsANewAccountWhenRequested() {
        commandBus.publish(CreateAccountCommand.aCreateAccountCommandWith(requestId, initialBalance))
        log.debug(accountRepository.toString())
    }

}