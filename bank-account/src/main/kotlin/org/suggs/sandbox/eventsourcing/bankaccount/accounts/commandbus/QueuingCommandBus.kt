package org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandbus

import com.google.common.eventbus.EventBus
import org.slf4j.LoggerFactory
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandhandler.CommandHandler
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command.Command

class QueuingCommandBus : CommandBus {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    private val eventBus: EventBus = EventBus("Event Bus for commands")

    override fun registerSubscriber(aCommandHandler: CommandHandler<*>) {
        log.debug("Registering CommandHandler $aCommandHandler")
        eventBus.register(aCommandHandler)
    }

    override fun publish(aCommand: Command) {
        log.debug("Posting command $aCommand")
        eventBus.post(aCommand)
    }
}