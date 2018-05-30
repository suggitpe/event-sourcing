package org.suggs.sandbox.eventsourcing.bankaccount.accounts.repository;

import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.events.Event;

public interface AccountRepository {

    void save(Event anEvent);

    int size();


}
