package org.suggs.sandbox.eventsourcing.backaccount.accounts;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;
import org.suggs.sandbox.eventsourcing.backaccount.accounts.controllers.AccountsControllerConfiguration;
import org.suggs.sandbox.eventsourcing.backaccount.accounts.service.AccountsAggregateConfiguration;

@SpringBootApplication
@Import({AccountsControllerConfiguration.class,
        AccountsAggregateConfiguration.class})
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .web(true)
                .run(args);
    }

}
