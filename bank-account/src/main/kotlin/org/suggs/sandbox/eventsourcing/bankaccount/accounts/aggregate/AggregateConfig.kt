package org.suggs.sandbox.eventsourcing.bankaccount.accounts.aggregate

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class AggregateConfig {

    @Bean
    open fun createCustomerAggregate() = CustomerAggregate()

    @Bean
    open fun createAccountAggregate() = AccountAggregate()
}