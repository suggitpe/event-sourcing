package org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandhandler;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.bus.CommandBus;
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command.ConfirmAccountToClient;
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command.CreateAccount;
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.AccountCreated;
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.repository.EventRepository;

import java.math.BigDecimal;
import java.util.UUID;

public class CreateAccountCommandHandler implements CommandHandler<CreateAccount> {

    private Logger LOG = LoggerFactory.getLogger(CreateAccountCommandHandler.class);

    private final CommandBus commandBus;
    private final EventRepository eventRepository;

    public CreateAccountCommandHandler(CommandBus commandBus, EventRepository eventRepository) {
        this.commandBus = commandBus;
        this.eventRepository = eventRepository;
    }

    @Override
    @Subscribe
    public void handle(CreateAccount aCommand) {
        UUID newAccountNumber = createNewAccount(aCommand.getRequestId(), aCommand.getInitialBalance());
        eventRepository.save(AccountCreated.Companion.anAccountCreatedEvent(aCommand.getRequestId(), newAccountNumber, aCommand.getInitialBalance()));
        commandBus.publish(ConfirmAccountToClient.Companion.confirmAccountToClient(aCommand.getRequestId(), newAccountNumber));
    }

    private UUID createNewAccount(UUID requestId, BigDecimal anInitialBalance) {
        LOG.debug("########### Creating a new account number for the request " + requestId.toString() + " with an initial balance of " + anInitialBalance);
        return UUID.randomUUID();
    }
}
