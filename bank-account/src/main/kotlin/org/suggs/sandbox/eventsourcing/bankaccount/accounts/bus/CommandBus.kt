package org.suggs.sandbox.eventsourcing.bankaccount.accounts.bus

import org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandhandler.CommandHandler
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command.Command

interface CommandBus {

    fun registerSubscriber(aCommand: CommandHandler<*>)

    fun publish(aCommand: Command)
}