package org.suggs.sandbox.eventsourcing.bankaccount.accounts.controllers

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandbus.QueuingCommandBus
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.eventstore.InMemoryEventStore

@Configuration
open class AccountsControllerConfig {

    @Bean
    open fun accountsRepository() = InMemoryEventStore()

    @Bean
    open fun accountBus() = QueuingCommandBus()
}