package org.suggs.sandbox.eventsourcing.bankaccount.accounts.projection

import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.Event

interface Projection {

    fun processEvent(event: Event)
}