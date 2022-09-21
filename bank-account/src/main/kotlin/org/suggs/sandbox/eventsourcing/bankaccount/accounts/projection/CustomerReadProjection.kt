package org.suggs.sandbox.eventsourcing.bankaccount.accounts.projection

import java.time.LocalDate

interface CustomerReadProjection: Projection {

    fun customerExists(firstName: String, lastName: String, dateOfBirth: LocalDate): Boolean

}