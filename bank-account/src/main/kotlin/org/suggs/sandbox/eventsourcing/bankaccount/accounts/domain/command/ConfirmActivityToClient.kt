package org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command

import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.Event
import java.util.*

data class ConfirmActivityToClient(val requestId: UUID, val customerEvent: Event) : Command