package org.suggs.sandbox.eventsourcing.bankaccount.accounts.controllers

import com.google.common.eventbus.EventBus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.bus.InternalCommandBus
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.repository.InMemoryAccountRepository

@Configuration
open class AccountsControllerConfig {

    @Bean
    open fun accountsRepository() = InMemoryAccountRepository()

    @Bean
    open fun accountBus() = InternalCommandBus(EventBus("Test Event Bus"))
}