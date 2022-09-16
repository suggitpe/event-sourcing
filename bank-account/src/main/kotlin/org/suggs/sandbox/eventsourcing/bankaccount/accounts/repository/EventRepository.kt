package org.suggs.sandbox.eventsourcing.bankaccount.accounts.repository

import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.Event

interface EventRepository {

    fun save(event: Event)

    fun size(): Int

    fun head(): Event

    fun read(): Event

    fun clear()
}
