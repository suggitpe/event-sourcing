package org.suggs.sandbox.eventsourcing.backaccount.accounts.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class AccountsAggregateConfiguration {

    @Bean
    public AccountsAggregate createAggregate() {
        return theCommand -> UUID.randomUUID().toString();
    }
}
