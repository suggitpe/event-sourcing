package org.suggs.sandbox.eventsourcing.backaccount.accounts.bus;

import com.google.common.eventbus.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandBusConfiguration {

    public EventBus createEventBus(){
        return new EventBus("Accounts Service Command Bus");
    }

    @Bean
    public CommandBus createCommandBus(){
        return new InternalCommandBus(createEventBus());
    }
}

