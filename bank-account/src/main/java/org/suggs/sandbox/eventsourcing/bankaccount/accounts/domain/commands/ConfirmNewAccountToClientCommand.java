package org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.commands;

import java.util.UUID;

public class ConfirmNewAccountToClientCommand implements Command {
    private final UUID accountNumber;
    private final UUID requestId;

    public ConfirmNewAccountToClientCommand(UUID createAccountRequestId, UUID newAccountNumber) {
        this.requestId = createAccountRequestId;
        accountNumber = newAccountNumber;
    }

    public UUID getRequestId() {
        return requestId;
    }

    public UUID getAccountNumber() {
        return accountNumber;
    }
}
