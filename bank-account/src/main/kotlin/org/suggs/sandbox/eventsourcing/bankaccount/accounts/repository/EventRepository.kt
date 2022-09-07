package org.suggs.sandbox.eventsourcing.bankaccount.accounts.repository

import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.events.Event

interface EventRepository {

    fun save(event: Event)

    fun size(): Int

}