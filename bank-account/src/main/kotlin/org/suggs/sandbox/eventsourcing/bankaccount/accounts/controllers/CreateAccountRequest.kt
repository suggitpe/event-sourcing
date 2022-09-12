package org.suggs.sandbox.eventsourcing.bankaccount.accounts.controllers

import java.math.BigDecimal

data class CreateAccountRequest(val initialBalance: BigDecimal)