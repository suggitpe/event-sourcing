package org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event

import java.util.*

data class CustomerRejected(val requestId: UUID, val reason: String): Event {
}