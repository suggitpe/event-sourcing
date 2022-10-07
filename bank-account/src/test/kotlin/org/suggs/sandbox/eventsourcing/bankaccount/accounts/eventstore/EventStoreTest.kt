package org.suggs.sandbox.eventsourcing.bankaccount.accounts.eventstore

import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.Account
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.AccountCreated
import java.math.BigDecimal.valueOf
import java.util.*

class EventStoreTest {

    companion object {
        private val requestId = UUID.randomUUID()
    }

    private val eventStore = InMemoryEventStore()

    @Test
    fun `saves events to the repository`() {
        eventStore.save(AccountCreated(requestId, Account(UUID.randomUUID(), UUID.randomUUID())))
        eventStore.size() shouldBe 1
        eventStore.head() shouldNotBe null
    }
}