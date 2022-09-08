package org.suggs.sandbox.eventsourcing.bankaccount.accounts.bus

import org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandhandler.CommandHandler
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command.Command

interface CommandBus {

    fun registerSubscriber(aCommandHandler: CommandHandler<*>)

    fun publish(aCommand: Command)
}