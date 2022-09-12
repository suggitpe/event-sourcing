package org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command

import java.time.LocalDate
import java.util.*

data class CreateNewCustomer(val requestId: UUID, val firstName: String, val lastName: String, val dateOfBirth: LocalDate) : Command