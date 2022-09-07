package org.suggs.sandbox.eventsourcing.bankaccount.accounts.repository

import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.Event
import java.util.*
import java.util.concurrent.LinkedBlockingQueue

class InMemoryEventRepository : EventRepository {

    private val eventQueue: Queue<Event> = LinkedBlockingQueue()

    override fun save(anEvent: Event) {
        eventQueue.add(anEvent)
    }

    override fun size(): Int {
        return eventQueue.size
    }
}