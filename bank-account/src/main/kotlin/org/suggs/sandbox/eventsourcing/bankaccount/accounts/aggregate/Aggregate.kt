package org.suggs.sandbox.eventsourcing.bankaccount.accounts.aggregate

import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.Event

interface Aggregate {

    fun observeEvent(event: Event)

}