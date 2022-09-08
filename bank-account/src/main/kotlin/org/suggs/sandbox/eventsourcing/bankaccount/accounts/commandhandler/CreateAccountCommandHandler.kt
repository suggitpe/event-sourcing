package org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandhandler

import com.google.common.eventbus.AllowConcurrentEvents
import com.google.common.eventbus.Subscribe
import org.slf4j.LoggerFactory
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.bus.CommandBus
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command.ConfirmAccountToClient
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command.CreateAccount
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.AccountCreated
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.repository.EventRepository
import java.math.BigDecimal
import java.util.*

class CreateAccountCommandHandler(private val eventRepo: EventRepository, private val commandBus: CommandBus) : CommandHandler<CreateAccount> {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    @Subscribe
    @AllowConcurrentEvents
    override fun handle(command: CreateAccount) {
        val accountNumber = createNewAccount(command.requestId, command.initialBalance)
        eventRepo.save(AccountCreated(command.requestId, accountNumber, command.initialBalance))
        commandBus.publish(ConfirmAccountToClient(command.requestId, accountNumber))
    }

    private fun createNewAccount(customerId: UUID, openingBalance: BigDecimal): UUID {
        log.debug("Creating a new account for the client [$customerId] with an opening balance of [$openingBalance]")
        return UUID.randomUUID()
    }
}