package org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event

import java.util.*

data class AccountConfirmedToClient(val requestId: UUID, val accountNumber: UUID): Event {
    companion object {
        fun anAccountConfirmedToClient(requestId: UUID, accountNumber: UUID) = AccountConfirmedToClient(requestId, accountNumber)
    }
}