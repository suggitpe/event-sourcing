package org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandhandler

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.bus.CommandBus
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.bus.CommandBusConfig
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.repository.EventRepository
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.repository.EventRepositoryConfig
import javax.inject.Inject

@Configuration
@Import(CommandBusConfig::class, EventRepositoryConfig::class)
open class CommandHandlerConfig {

    @Inject
    private lateinit var commandBus: CommandBus

    @Inject
    private lateinit var eventRepo: EventRepository

    @Bean
    open fun registerHandlers() {
        commandBus.registerSubscriber(CreateAccountHandler(eventRepo, commandBus))
        commandBus.registerSubscriber(ConfirmActivityToClientHandler(eventRepo, commandBus))
        commandBus.registerSubscriber(CreateCustomerHandler(eventRepo, commandBus))
    }

}