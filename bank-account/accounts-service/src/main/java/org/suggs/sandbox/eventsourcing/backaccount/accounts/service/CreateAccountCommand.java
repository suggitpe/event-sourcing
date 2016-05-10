package org.suggs.sandbox.eventsourcing.backaccount.accounts.service;

import java.math.BigDecimal;

public class CreateAccountCommand {
    private final BigDecimal initialBalance;

    public CreateAccountCommand(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }
}
