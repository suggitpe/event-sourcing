package org.suggs.sandbox.eventsourcing.backaccount.accounts.domain;

public class AccountCreatedEvent {
    private final String accountNumber;

    public AccountCreatedEvent(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
