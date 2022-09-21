package org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandhandler

import com.google.common.eventbus.AllowConcurrentEvents
import com.google.common.eventbus.Subscribe
import org.slf4j.LoggerFactory
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandbus.CommandBus
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.Account
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command.ConfirmActivityToClient
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command.CreateNewAccount
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.AccountCreated
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.eventstore.EventStore
import java.math.BigDecimal
import java.util.*

class CreateAccountHandler(private val eventRepo: EventStore, private val commandBus: CommandBus) : CommandHandler<CreateNewAccount> {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    @Subscribe
    @AllowConcurrentEvents
    override fun handle(command: CreateNewAccount) {
        val account = createNewAccount(command.customerId, command.initialBalance)
        val event = AccountCreated(command.requestId, account, command.initialBalance)
        eventRepo.save(event)
        commandBus.publish(ConfirmActivityToClient(command.requestId, event))
    }

    private fun createNewAccount(customerId: UUID, openingBalance: BigDecimal): Account {
        log.debug("Creating a new account for the client [$customerId] with an opening balance of [$openingBalance]")
        return Account(UUID.randomUUID(), customerId)
    }
}