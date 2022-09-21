package org.suggs.sandbox.eventsourcing.bankaccount.accounts.eventstore

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class EventStoreConfig {

    @Bean
    open fun createRepository() = InMemoryEventStore()
}