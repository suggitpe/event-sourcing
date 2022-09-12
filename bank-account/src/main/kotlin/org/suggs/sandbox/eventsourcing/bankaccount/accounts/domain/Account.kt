package org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain

import java.util.*

data class Account(val accountId: UUID, val customerId: UUID)