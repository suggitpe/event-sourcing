package org.suggs.sandbox.eventsourcing.bankaccount.accounts.repository

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.events.AccountCreationRequestedEvent
import java.math.BigDecimal.valueOf
import java.util.*
import javax.inject.Inject

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [AccountRepositoryConfiguration::class])
class AccountRepositoryTest {
    companion object {
        private val requestId = UUID.randomUUID()
        private val ONE_THOUSAND = valueOf(1000.00)
    }

    @Inject
    lateinit var accountRepository: AccountRepository

    @Test
    fun savesEventsToTheRepository() {
        accountRepository.save(AccountCreationRequestedEvent.anAccountCreationRequestedEventFor(requestId, ONE_THOUSAND))
        accountRepository.size() shouldBe 1
    }
}