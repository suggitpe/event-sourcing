package org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandhandler

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.aggregate.AggregateConfig
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.aggregate.CustomerAggregate
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandbus.CommandBus
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandbus.CommandBusConfig
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.eventstore.EventStore
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.eventstore.EventStoreConfig
import javax.inject.Inject

@Configuration
@Import(CommandBusConfig::class, EventStoreConfig::class, AggregateConfig::class)
open class CommandHandlerConfig {

    @Inject
    private lateinit var commandBus: CommandBus

    @Inject
    private lateinit var eventStore: EventStore

    @Inject
    private lateinit var customers: CustomerAggregate

    @Bean
    open fun registerHandlers() {
        commandBus.registerSubscriber(CreateAccountHandler(eventStore, commandBus))
        commandBus.registerSubscriber(ConfirmActivityToClientHandler(eventStore, commandBus))
        commandBus.registerSubscriber(CreateCustomerHandler(eventStore, customers))
    }

    @Bean
    open fun registerAggregates(){
        eventStore.registerAggregate(customers)
    }

}