package org.suggs.sandbox.eventsourcing.backaccount.accounts.controllers;

import java.util.UUID;

public class CreateAccountResponse {

    private UUID requestId;

    public CreateAccountResponse() {
    }

    public CreateAccountResponse(UUID requestId) {
        this.requestId = requestId;
    }

    public UUID getRequestId() {
        return requestId;
    }

    public void setRequestId(UUID requestId) {
        this.requestId = requestId;
    }
}
