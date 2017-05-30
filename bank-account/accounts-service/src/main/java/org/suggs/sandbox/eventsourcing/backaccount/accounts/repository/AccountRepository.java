package org.suggs.sandbox.eventsourcing.backaccount.accounts.repository;

import org.suggs.sandbox.eventsourcing.backaccount.accounts.domain.events.Event;

public interface AccountRepository {

    void save(Event anEvent);

    int size();


}
