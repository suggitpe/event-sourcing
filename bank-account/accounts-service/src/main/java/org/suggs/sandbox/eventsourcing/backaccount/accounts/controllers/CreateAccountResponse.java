package org.suggs.sandbox.eventsourcing.backaccount.accounts.controllers;

public class CreateAccountResponse {

    private String accountId;

    public CreateAccountResponse() {
    }

    public CreateAccountResponse(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
