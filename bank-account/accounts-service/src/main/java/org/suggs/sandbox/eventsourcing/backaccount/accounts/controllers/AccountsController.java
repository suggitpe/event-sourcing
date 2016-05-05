package org.suggs.sandbox.eventsourcing.backaccount.accounts.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {

    private static final Logger LOG = LoggerFactory.getLogger(AccountsController.class);

    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
    public CreateAccountResponse createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        LOG.info("Called with " + createAccountRequest.getInitialBalance());
        return new CreateAccountResponse("foo1234");
    }
}
