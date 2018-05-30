package org.suggs.sandbox.eventsourcing.bankaccount.accounts.controllers;

import java.util.UUID;

public class RequestResponse {

    private final UUID requestId;

    public static RequestResponse aResponseForRequestWithIdOf(UUID anId) {
        return new RequestResponse(anId);
    }

    private RequestResponse(UUID requestId) {
        this.requestId = requestId;
    }

    public UUID getRequestId() {
        return requestId;
    }

}
