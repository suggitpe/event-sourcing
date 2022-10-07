package org.suggs.sandbox.eventsourcing.bankaccount.accounts.aggregate

import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command.Command
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.Event

interface Aggregate<T : Command> {

    /**
     * Executes on the observation of an event, for example it may add the event to the aggregate root.
     */
    fun observeEvent(event: Event)

    /**
     * Executes commands of type T
     */
    fun execute(command: T): Event

}