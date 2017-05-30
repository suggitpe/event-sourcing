package org.suggs.sandbox.eventsourcing.backaccount.accounts.bus;

import org.suggs.sandbox.eventsourcing.backaccount.accounts.commandhandler.CommandHandler;
import org.suggs.sandbox.eventsourcing.backaccount.accounts.domain.commands.Command;

public interface CommandBus {

    void registerSubscriber(CommandHandler aCommand);

    void publish(Command aCommand);
}
