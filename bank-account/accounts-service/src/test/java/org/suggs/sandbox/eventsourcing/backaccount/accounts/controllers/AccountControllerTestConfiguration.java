package org.suggs.sandbox.eventsourcing.backaccount.accounts.controllers;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.suggs.sandbox.eventsourcing.backaccount.accounts.service.AccountsAggregate;

import java.util.UUID;

@Configuration
@Import({AccountsControllerConfiguration.class})
@EnableAutoConfiguration
@ComponentScan
public class AccountControllerTestConfiguration {

    @Bean
    public AccountsAggregate accountsAggregate() {
        return command -> UUID.randomUUID().toString();
    }
}
