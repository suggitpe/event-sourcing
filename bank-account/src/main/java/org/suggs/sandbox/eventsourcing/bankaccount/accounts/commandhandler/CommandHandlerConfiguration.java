package org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandhandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.bus.CommandBus;
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.bus.CommandBusConfiguration;
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.repository.AccountRepository;
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.repository.AccountRepositoryConfiguration;

import javax.inject.Inject;

@Configuration
@Import({CommandBusConfiguration.class, AccountRepositoryConfiguration.class})
public class CommandHandlerConfiguration {

    @Inject
    private CommandBus commandBus;
    @Inject
    private AccountRepository accountRepository;

    @Bean
    public CommandHandler createAccountRequestHandler() {
        CommandHandler handler = new CreateAccountCommandHandler(commandBus, accountRepository);
        commandBus.registerSubscriber(handler);
        return handler;
    }

    @Bean
    public CommandHandler createConfirmToClientHandler(){
        CommandHandler handler = new ConfirmAccountToClientHandler(accountRepository);
        commandBus.registerSubscriber(handler);
        return handler;
    }

}
