package org.suggs.sandbox.eventsourcing.backaccount.accounts.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountRepositoryConfiguration {

    @Bean
    public AccountRepository createRepository() {
        return new InMemoryAccountRepository();
    }
}
