package org.suggs.sandbox.eventsourcing.bankaccount.accounts.aggregate

import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command.CreateNewAccount
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.AccountCreated
import java.math.BigDecimal.valueOf
import java.util.*

class AccountAggregateTest {

    private val requestId: UUID = UUID.randomUUID()
    private val customerId: UUID = UUID.randomUUID()
    private lateinit var accounts: Aggregate<CreateNewAccount>
    private val command = CreateNewAccount(requestId, customerId, valueOf(10000))

    @BeforeEach
    fun initialise() {
        accounts = AccountAggregate()
    }

    @Test
    fun `creates new account when accounts does not exist`() {
        accounts.execute(command).shouldBeInstanceOf<AccountCreated>()
    }
}