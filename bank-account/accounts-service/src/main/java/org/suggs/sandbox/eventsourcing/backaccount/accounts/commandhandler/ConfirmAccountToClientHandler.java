package org.suggs.sandbox.eventsourcing.backaccount.accounts.commandhandler;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.suggs.sandbox.eventsourcing.backaccount.accounts.domain.commands.ConfirmNewAccountToClientCommand;
import org.suggs.sandbox.eventsourcing.backaccount.accounts.domain.events.ConfirmedAccountNumberToClientEvent;
import org.suggs.sandbox.eventsourcing.backaccount.accounts.repository.AccountRepository;

import java.util.UUID;

public class ConfirmAccountToClientHandler implements CommandHandler<ConfirmNewAccountToClientCommand> {

    private static final Logger LOG = LoggerFactory.getLogger(ConfirmAccountToClientHandler.class);
    private AccountRepository accountRepository;

    public ConfirmAccountToClientHandler(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Subscribe
    @AllowConcurrentEvents
    public void handle(ConfirmNewAccountToClientCommand aCommand) {
        confirmNewAccountNumberToClient(aCommand.getRequestId(), aCommand.getAccountNumber());
        accountRepository.save(new ConfirmedAccountNumberToClientEvent(aCommand.getRequestId(), aCommand.getAccountNumber()));
    }

    private void confirmNewAccountNumberToClient(UUID requestId, UUID accountNumber) {
        LOG.debug("Emailing the client that we have finished setting up the new account for them (yay!!!");
    }
}
