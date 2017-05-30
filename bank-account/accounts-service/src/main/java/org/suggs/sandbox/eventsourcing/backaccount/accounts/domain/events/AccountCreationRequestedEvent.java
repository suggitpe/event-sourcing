package org.suggs.sandbox.eventsourcing.backaccount.accounts.domain.events;

import java.math.BigDecimal;
import java.util.UUID;

public class AccountCreationRequestedEvent implements Event {
    private final UUID createAccountRequestId;
    private final BigDecimal initialBalance;

    public static AccountCreationRequestedEvent anAccountCreationRequestedEventFor(UUID createAccountRequestId, BigDecimal initialBalance) {
        return new AccountCreationRequestedEvent(createAccountRequestId, initialBalance);
    }

    private AccountCreationRequestedEvent(UUID createAccountRequestId, BigDecimal initialBalance) {
        this.createAccountRequestId = createAccountRequestId;
        this.initialBalance = initialBalance;
    }

    public UUID getCreateAccountRequestId() {
        return createAccountRequestId;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    @Override
    public String toString() {
        return "AccountCreationRequestedEvent{" +
                "createAccountRequestId=" + createAccountRequestId +
                ", initialBalance=" + initialBalance +
                '}';
    }
}
