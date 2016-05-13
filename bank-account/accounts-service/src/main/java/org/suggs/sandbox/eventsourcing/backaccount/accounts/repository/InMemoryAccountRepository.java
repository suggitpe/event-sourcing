package org.suggs.sandbox.eventsourcing.backaccount.accounts.repository;

import org.suggs.sandbox.eventsourcing.backaccount.accounts.domain.Event;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class InMemoryAccountRepository implements AccountRepository {

    private Queue<Event> eventQueue = new LinkedBlockingQueue<>();

    @Override
    public void save(Event anEvent) {
        eventQueue.add(anEvent);
    }

    @Override
    public int size() {
        return eventQueue.size();
    }
}
