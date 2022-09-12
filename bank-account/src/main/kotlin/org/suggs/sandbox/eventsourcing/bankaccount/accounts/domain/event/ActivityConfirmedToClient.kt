package org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event

import java.util.*

data class ActivityConfirmedToClient(val requestId: UUID, val customerEvent: Event) : Event