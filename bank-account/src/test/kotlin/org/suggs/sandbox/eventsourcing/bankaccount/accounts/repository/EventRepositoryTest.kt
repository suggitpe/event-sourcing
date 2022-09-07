package org.suggs.sandbox.eventsourcing.bankaccount.accounts.repository

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.events.AccountCreationRequestedEvent.anAccountCreationRequestedEventFor
import java.math.BigDecimal.valueOf
import java.util.*
import javax.inject.Inject

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [EventRepositoryConfig::class])
class EventRepositoryTest {
    companion object {
        private val requestId = UUID.randomUUID()
        private val ONE_THOUSAND = valueOf(1000.00)
    }

    @Inject
    private lateinit var eventRepository: EventRepository

    @Test
    fun `saves events to the repository`() {
        eventRepository.save(anAccountCreationRequestedEventFor(requestId, ONE_THOUSAND))
        eventRepository.size() shouldBe 1
    }
}