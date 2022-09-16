package org.suggs.sandbox.eventsourcing.bankaccount.accounts.repository

import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.Event
import java.util.*
import java.util.concurrent.LinkedBlockingQueue

class InMemoryEventRepository : EventRepository {

    private val eventQueue: Queue<Event> = LinkedBlockingQueue()

    override fun save(event: Event) {
        eventQueue.add(event)
    }

    override fun size(): Int {
        return eventQueue.size
    }

    override fun head(): Event {
        return eventQueue.peek()
    }

    override fun read(): Event {
        return eventQueue.remove()
    }

    override fun clear() {
        eventQueue.clear()
    }
}