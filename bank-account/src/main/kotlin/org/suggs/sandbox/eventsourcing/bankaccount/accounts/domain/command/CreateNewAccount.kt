package org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command

import java.math.BigDecimal
import java.util.*

data class CreateNewAccount(val requestId: UUID, val customerId: UUID, val initialBalance: BigDecimal) : Command