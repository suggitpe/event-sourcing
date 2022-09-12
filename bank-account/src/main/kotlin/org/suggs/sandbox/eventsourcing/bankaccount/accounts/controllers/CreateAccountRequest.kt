package org.suggs.sandbox.eventsourcing.bankaccount.accounts.controllers

import java.math.BigDecimal
import java.util.*

data class CreateAccountRequest(val customerId: UUID, val initialBalance: BigDecimal)