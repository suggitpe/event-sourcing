package org.suggs.sandbox.eventsourcing.bankaccount.accounts.eventstore

import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.Event

interface EventStore {

    fun save(event: Event)

    fun size(): Int

    fun head(): Event

    fun read(): Event

    fun clear()

    fun registerProjection()
}
