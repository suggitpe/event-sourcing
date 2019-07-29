package org.suggs.sandbox.eventsourcing.bankaccount.accounts.bus;

import org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandhandler.CommandHandler;
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.commands.Command;

public interface CommandBus {

    void registerSubscriber(CommandHandler aCommand);

    void publish(Command aCommand);
}
