package org.suggs.sandbox.eventsourcing.backaccount.accounts.bus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.suggs.sandbox.eventsourcing.backaccount.accounts.domain.Command;

public class InternalCommandBus {

    private static final Logger LOG = LoggerFactory.getLogger(InternalCommandBus.class);

    public void publish(Command aCommand) {
        LOG.debug("########### someone just published a command:" + aCommand.toString());
    }
}
