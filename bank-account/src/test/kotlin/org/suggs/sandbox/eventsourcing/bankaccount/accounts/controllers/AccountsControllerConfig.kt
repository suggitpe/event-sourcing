package org.suggs.sandbox.eventsourcing.bankaccount.accounts.controllers

import com.google.common.eventbus.EventBus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.bus.QueuingCommandBus
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.repository.InMemoryEventRepository

@Configuration
open class AccountsControllerConfig {

    @Bean
    open fun accountsRepository() = InMemoryEventRepository()

    @Bean
    open fun accountBus() = QueuingCommandBus()
}