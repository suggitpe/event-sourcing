package org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandhandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.bus.CommandBus;
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.bus.CommandBusConfiguration;
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.repository.EventRepository;
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.repository.EventRepositoryConfig;

import javax.inject.Inject;

@Configuration
@Import({CommandBusConfiguration.class, EventRepositoryConfig.class})
public class CommandHandlerConfiguration {

    @Inject
    private CommandBus commandBus;
    @Inject
    private EventRepository eventRepository;

    @Bean
    public CommandHandler createAccountRequestHandler() {
        CommandHandler handler = new CreateAccountCommandHandler(commandBus, eventRepository);
        commandBus.registerSubscriber(handler);
        return handler;
    }

    @Bean
    public CommandHandler createConfirmToClientHandler() {
        CommandHandler handler = new ConfirmAccountToClientHandler(eventRepository);
        commandBus.registerSubscriber(handler);
        return handler;
    }

}
