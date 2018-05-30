package org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.commands.Command;

public class DummyCommandHandler implements CommandHandler {

    private static final Logger LOG = LoggerFactory.getLogger(DummyCommandHandler.class);

    @Override
    public void handle(Command aCommand) {
        LOG.debug("Processing Command " + aCommand);
    }
}
