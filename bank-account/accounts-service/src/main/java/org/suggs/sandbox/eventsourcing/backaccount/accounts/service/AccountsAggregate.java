package org.suggs.sandbox.eventsourcing.backaccount.accounts.service;

public interface AccountsAggregate {
    public String createAccount(CreateAccountCommand createAccountCommand);
}
