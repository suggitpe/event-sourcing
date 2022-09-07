package org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event

import java.math.BigDecimal
import java.util.*

data class AccountCreated(val requestId: UUID, val accountNumber: UUID, val initialBalance: BigDecimal): Event {
    companion object {
        fun anAccountCreatedEvent(requestId: UUID, accountNumber: UUID, initialBalance: BigDecimal)= AccountCreated(requestId, accountNumber, initialBalance)
    }
}