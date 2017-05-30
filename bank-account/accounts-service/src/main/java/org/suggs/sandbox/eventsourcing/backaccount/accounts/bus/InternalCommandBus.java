package org.suggs.sandbox.eventsourcing.backaccount.accounts.bus;

import com.google.common.eventbus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.suggs.sandbox.eventsourcing.backaccount.accounts.commandhandler.CommandHandler;
import org.suggs.sandbox.eventsourcing.backaccount.accounts.domain.commands.Command;

public class InternalCommandBus implements CommandBus {

    private static final Logger LOG = LoggerFactory.getLogger(InternalCommandBus.class);
    private final EventBus eventBus;

    public InternalCommandBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void publish(Command aCommand) {
        eventBus.post(aCommand);
        LOG.debug("---> someone just published a command:" + aCommand.toString());
    }

    public void registerSubscriber(CommandHandler aCommandHandler) {
        LOG.debug("Registered [" + aCommandHandler + "] with the event bus");
        eventBus.register(aCommandHandler);
    }
}
