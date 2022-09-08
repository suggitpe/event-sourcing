package org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandhandler

import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command.Command

interface CommandHandler<T : Command> {

    fun handle(command: T)
}