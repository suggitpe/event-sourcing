package org.suggs.sandbox.eventsourcing.bankaccount.accounts.repository

import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.Account
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.AccountCreated
import java.math.BigDecimal.valueOf
import java.util.*

class EventRepositoryTest {
    companion object {
        private val requestId = UUID.randomUUID()
        private val ONE_THOUSAND = valueOf(1000.00)
    }

    private val eventRepository = InMemoryEventRepository()

    @Test
    fun `saves events to the repository`() {
        eventRepository.save(AccountCreated(requestId, Account(UUID.randomUUID(), UUID.randomUUID()), ONE_THOUSAND))
        eventRepository.size() shouldBe 1
        eventRepository.head() shouldNotBe null
    }
}