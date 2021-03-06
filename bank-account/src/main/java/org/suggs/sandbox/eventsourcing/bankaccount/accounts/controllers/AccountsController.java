package org.suggs.sandbox.eventsourcing.bankaccount.accounts.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.bus.CommandBus;
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.repository.AccountRepository;

import javax.inject.Inject;
import java.util.UUID;

import static org.suggs.sandbox.eventsourcing.bankaccount.accounts.controllers.RequestResponse.aResponseForRequestWithIdOf;
import static org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.events.AccountCreationRequestedEvent.anAccountCreationRequestedEventFor;
import static org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.commands.CreateAccountCommand.aCreateAccountCommandWith;

@RestController
public class AccountsController {

    private static final Logger LOG = LoggerFactory.getLogger(AccountsController.class);

    @Inject
    private AccountRepository accountRepository;
    @Inject
    private CommandBus commandBus;

    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
    public RequestResponse createAccount(@Validated @RequestBody CreateAccountRequest createAccountRequest) {
        UUID createAccountRequestId = UUID.randomUUID();
        commandBus.publish(aCreateAccountCommandWith(createAccountRequestId, createAccountRequest.getInitialBalance()));
        accountRepository.save(anAccountCreationRequestedEventFor(createAccountRequestId, createAccountRequest.getInitialBalance()));
        return aResponseForRequestWithIdOf(createAccountRequestId);
    }
}
