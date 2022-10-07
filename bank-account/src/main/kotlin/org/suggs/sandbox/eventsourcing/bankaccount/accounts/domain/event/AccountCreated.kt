package org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event

import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.Account
import java.math.BigDecimal
import java.util.*

data class AccountCreated(val requestId: UUID, val account: Account) : Event