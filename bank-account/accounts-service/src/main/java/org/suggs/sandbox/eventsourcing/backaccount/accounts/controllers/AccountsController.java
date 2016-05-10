package org.suggs.sandbox.eventsourcing.backaccount.accounts.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.suggs.sandbox.eventsourcing.backaccount.accounts.service.AccountsAggregate;
import org.suggs.sandbox.eventsourcing.backaccount.accounts.service.CreateAccountCommand;

import javax.inject.Inject;

@RestController
public class AccountsController {

    private static final Logger LOG = LoggerFactory.getLogger(AccountsController.class);

    @Inject
    private AccountsAggregate accountsAggregate;

    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
    public CreateAccountResponse createAccount(@Validated @RequestBody CreateAccountRequest createAccountRequest) {
        String accountNumber = accountsAggregate.createAccount(new CreateAccountCommand(createAccountRequest.getInitialBalance()));
        return new CreateAccountResponse(accountNumber);
    }
}