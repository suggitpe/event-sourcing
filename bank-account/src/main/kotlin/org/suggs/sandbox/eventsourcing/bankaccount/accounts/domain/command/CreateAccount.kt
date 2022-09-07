package org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command

import java.math.BigDecimal
import java.util.*

data class CreateAccount(val requestId: UUID, val initialBalance: BigDecimal): Command {
    companion object {
        fun newAccount(requestId: UUID, initialBalance: BigDecimal) = CreateAccount(requestId, initialBalance)
    }
}