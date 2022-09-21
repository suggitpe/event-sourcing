package org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandhandler

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandbus.CommandBus
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandbus.CommandBusConfig
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.projection.CustomerReadProjection
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.projection.ProjectionConfig
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.eventstore.EventStore
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.eventstore.EventStoreConfig
import javax.inject.Inject

@Configuration
@Import(CommandBusConfig::class, EventStoreConfig::class, ProjectionConfig::class)
open class CommandHandlerConfig {

    @Inject
    private lateinit var commandBus: CommandBus

    @Inject
    private lateinit var eventRepo: EventStore

    @Inject
    private lateinit var customerRead: CustomerReadProjection

    @Bean
    open fun registerHandlers() {
        commandBus.registerSubscriber(CreateAccountHandler(eventRepo, commandBus))
        commandBus.registerSubscriber(ConfirmActivityToClientHandler(eventRepo, commandBus))
        commandBus.registerSubscriber(CreateCustomerHandler(eventRepo, commandBus, customerRead))
    }

}