package org.suggs.sandbox.eventsourcing.backaccount.accounts.controllers;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AccountsControllerConfiguration.class})
@EnableAutoConfiguration
@ComponentScan
public class AccountControllerTestConfiguration {
}
