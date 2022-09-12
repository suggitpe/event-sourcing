package org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain

import java.time.LocalDate
import java.util.*

data class Customer(val customerId: UUID, val firstName: String, val lastName: String, val dateOfBirth: LocalDate)