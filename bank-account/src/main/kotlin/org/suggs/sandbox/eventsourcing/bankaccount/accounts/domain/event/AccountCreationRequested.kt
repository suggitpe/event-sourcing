package org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event

import java.math.BigDecimal
import java.util.*

data class AccountCreationRequested(val requestId: UUID, val initialBalance: BigDecimal) : Event {
    companion object {
        fun anAccountCreationRequestedEventFor(requestId: UUID, initialBalance: BigDecimal) = AccountCreationRequested(requestId, initialBalance)
    }
}