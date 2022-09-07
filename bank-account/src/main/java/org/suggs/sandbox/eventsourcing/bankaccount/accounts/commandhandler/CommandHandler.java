package org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandhandler;

import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command.Command;

public interface CommandHandler<T extends Command> {

    void handle(T aCommand);
}
