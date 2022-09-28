package org.suggs.sandbox.eventsourcing.bankaccount.accounts.eventstore

import org.suggs.sandbox.eventsourcing.bankaccount.accounts.aggregate.Aggregate
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.Event
import java.util.*
import java.util.concurrent.LinkedBlockingDeque

class InMemoryEventStore : EventStore {

    private val eventQueue: Queue<Event> = LinkedBlockingDeque()
    private var aggregates: List<Aggregate<*>> = listOf()

    override fun save(event: Event) {
        eventQueue.add(event)
        aggregates.map { it.observeEvent(event) }
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

    override fun registerAggregate(aggregate: Aggregate<*>) {
        aggregates = aggregates + aggregate
    }
}