package org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandhandler

import com.google.common.eventbus.AllowConcurrentEvents
import com.google.common.eventbus.Subscribe
import org.slf4j.LoggerFactory
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandbus.CommandBus
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command.ConfirmActivityToClient
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.AccountCreated
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.ActivityConfirmedToClient
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.CustomerCreated
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.Event
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.eventstore.EventStore

class ConfirmActivityToClientHandler(private val eventRepo: EventStore, private val commandBus: CommandBus) : CommandHandler<ConfirmActivityToClient> {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    @Subscribe
    @AllowConcurrentEvents
    override fun handle(command: ConfirmActivityToClient) {
        confirmActivityToClient(command.customerEvent)
        eventRepo.save(ActivityConfirmedToClient(command.requestId, command.customerEvent))
    }

    private fun confirmActivityToClient(event: Event) {
        when (event) {
            is CustomerCreated -> log.debug("informing of a new customer")
            is AccountCreated -> log.debug("informing of a new account")
        }
        log.info("Emailing client to inform them of recent activity [$event] for them")
    }
}