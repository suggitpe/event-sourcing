package org.suggs.sandbox.eventsourcing.bankaccount.accounts.repository;

import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.events.Event;

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

    @Override
    public String toString(){
        StringBuffer buf = new StringBuffer().append("\n");
        eventQueue.stream().forEach(event -> buf.append(event.toString()).append("\n"));
        return buf.toString();
    }
}
