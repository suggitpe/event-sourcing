package org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event

import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.Customer
import java.util.*

data class CustomerCreated(val requestId: UUID, val customer: Customer) : Event