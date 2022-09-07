package org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandhandler;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command.ConfirmAccountToClient;
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.AccountConfirmedToClient;
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.repository.EventRepository;

import java.util.UUID;

public class ConfirmAccountToClientHandler implements CommandHandler<ConfirmAccountToClient> {

    private static final Logger LOG = LoggerFactory.getLogger(ConfirmAccountToClientHandler.class);
    private EventRepository eventRepository;

    public ConfirmAccountToClientHandler(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    @Subscribe
    @AllowConcurrentEvents
    public void handle(ConfirmAccountToClient aCommand) {
        confirmNewAccountNumberToClient(aCommand.getRequestId(), aCommand.getAccountNumber());
        eventRepository.save(AccountConfirmedToClient.Companion.anAccountConfirmedToClient(aCommand.getRequestId(), aCommand.getAccountNumber()));
    }

    private void confirmNewAccountNumberToClient(UUID requestId, UUID accountNumber) {
        LOG.debug("Emailing the client that we have finished setting up the new account for them (yay!!!");
    }
}
