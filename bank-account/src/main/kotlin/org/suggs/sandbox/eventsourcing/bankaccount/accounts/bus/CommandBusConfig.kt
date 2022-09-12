package org.suggs.sandbox.eventsourcing.bankaccount.accounts.bus

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class CommandBusConfig {

    @Bean
    open fun createCommandBus() = QueuingCommandBus()
}