package org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command

import java.util.*

data class ConfirmAccountToClient(val requestId: UUID, val accountNumber: UUID) : Command {
    companion object {
        fun confirmAccountToClient(requestId: UUID, accountNumber: UUID) = ConfirmAccountToClient(requestId, accountNumber)
    }
}