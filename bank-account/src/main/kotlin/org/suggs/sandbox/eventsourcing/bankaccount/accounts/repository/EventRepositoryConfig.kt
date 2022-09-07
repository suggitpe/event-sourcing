package org.suggs.sandbox.eventsourcing.bankaccount.accounts.repository

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class EventRepositoryConfig {

    @Bean
    open fun createRepository() = InMemoryEventRepository()
}