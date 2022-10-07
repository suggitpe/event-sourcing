package org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandhandler

import com.google.common.eventbus.AllowConcurrentEvents
import com.google.common.eventbus.Subscribe
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.aggregate.Aggregate
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command.CreateNewAccount
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.eventstore.EventStore

class CreateAccountHandler(private val eventRepo: EventStore, private val accounts: Aggregate<CreateNewAccount>) : CommandHandler<CreateNewAccount> {

    @Subscribe
    @AllowConcurrentEvents
    override fun handle(command: CreateNewAccount) {
        eventRepo.save(accounts.execute(command))
    }

}