package org.suggs.sandbox.eventsourcing.bankaccount.accounts.controllers;

import com.google.common.eventbus.EventBus;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.bus.InternalCommandBus;
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.repository.AccountRepository;
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.repository.InMemoryAccountRepository;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class AccountControllerTestConfiguration {

    @Bean
    public AccountRepository accountsRepository() {
        return new InMemoryAccountRepository();
    }

    @Bean
    public InternalCommandBus accountBus() {
        return new InternalCommandBus(new EventBus("Test Event Bus"));
    }
}
