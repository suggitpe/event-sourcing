package org.suggs.sandbox.eventsourcing.backaccount.accounts.commandhandler;

import org.suggs.sandbox.eventsourcing.backaccount.accounts.domain.commands.Command;

public interface CommandHandler<T extends Command> {

    void handle(T aCommand);
}
