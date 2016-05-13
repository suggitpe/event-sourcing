package org.suggs.sandbox.eventsourcing.backaccount.accounts.domain;

import java.math.BigDecimal;
import java.util.UUID;

public class CreateAccountCommand implements Command {
    private final UUID createAccountRequestId;
    private final BigDecimal initialBalance;

    public static CreateAccountCommand aCreateAccountCommandWith(UUID createAccountRequestId, BigDecimal initialBalance) {
        return new CreateAccountCommand(createAccountRequestId, initialBalance);
    }

    private CreateAccountCommand(UUID createAccountRequestId, BigDecimal initialBalance) {
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
        return "CreateAccountCommand{" +
                "createAccountRequestId=" + createAccountRequestId +
                ", initialBalance=" + initialBalance +
                '}';
    }
}
