package org.suggs.sandbox.eventsourcing.bankaccount.accounts.aggregate

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.eventstore.EventStoreConfig

@Configuration
open class AggregateConfig {

    @Bean
    open fun createCustomerAggregate() = CachedCustomerAggregate()
}