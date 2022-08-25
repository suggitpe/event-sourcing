package org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.events;

import java.util.UUID;

public class ConfirmedAccountNumberToClientEvent implements Event {
    private final UUID requestId;
    private final UUID accountNumber;

    public ConfirmedAccountNumberToClientEvent(UUID requestId, UUID accountNumber) {
        this.requestId = requestId;
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "ConfirmedAccountNumberToClientEvent{" +
                "requestId=" + requestId +
                ", accountNumber=" + accountNumber +
                '}';
    }
}
