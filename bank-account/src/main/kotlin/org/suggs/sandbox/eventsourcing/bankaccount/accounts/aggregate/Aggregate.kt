package org.suggs.sandbox.eventsourcing.bankaccount.accounts.aggregate

import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command.Command
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.Event

interface Aggregate<T : Command> {

    fun observeEvent(event: Event)

    fun execute(command: T): Event

}