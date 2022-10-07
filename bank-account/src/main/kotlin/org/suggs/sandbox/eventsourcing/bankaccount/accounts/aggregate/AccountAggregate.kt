package org.suggs.sandbox.eventsourcing.bankaccount.accounts.aggregate

import org.slf4j.LoggerFactory
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.Account
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command.CreateNewAccount
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.AccountCreated
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.Event
import java.util.*

class AccountAggregate : Aggregate<CreateNewAccount> {

    private var accounts: Map<UUID, Account> = mapOf()

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    override fun observeEvent(event: Event) {
        when (event) {
            is AccountCreated -> accounts = accounts + mapOf(event.account.accountId to event.account)
        }
    }

    override fun execute(command: CreateNewAccount): Event {
        return AccountCreated(command.requestId, Account(UUID.randomUUID(), UUID.randomUUID()))
    }
}