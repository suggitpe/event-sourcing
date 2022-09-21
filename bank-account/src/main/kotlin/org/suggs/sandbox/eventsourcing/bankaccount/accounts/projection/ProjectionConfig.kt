package org.suggs.sandbox.eventsourcing.bankaccount.accounts.projection

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.eventstore.EventStoreConfig

@Configuration
@Import(EventStoreConfig::class)
open class ProjectionConfig {

    @Bean
    open fun createCustomerProjection() = InMemoryCustomerProjection()
}