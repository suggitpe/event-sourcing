package org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.events;

import java.math.BigDecimal;
import java.util.UUID;

public class AccountCreatedEvent implements Event {
    private final UUID requestId;
    private final UUID accountNumber;
    private final BigDecimal initialBalance;

    public AccountCreatedEvent(UUID requestId, UUID accountNumber, BigDecimal initialBalance) {
        this.requestId = requestId;
        this.accountNumber = accountNumber;
        this.initialBalance = initialBalance;
    }

    public UUID getRequestId() {
        return requestId;
    }

    public UUID getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    @Override
    public String toString() {
        return "AccountCreatedEvent{" +
                "requestId=" + requestId +
                ", accountNumber=" + accountNumber +
                ", initialBalance=" + initialBalance +
                '}';
    }
}
