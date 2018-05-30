package org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandhandler;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.bus.CommandBus;
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.commands.ConfirmNewAccountToClientCommand;
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.commands.CreateAccountCommand;
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.events.AccountCreatedEvent;
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.UUID;

public class CreateAccountCommandHandler implements CommandHandler<CreateAccountCommand> {

    private Logger LOG = LoggerFactory.getLogger(CreateAccountCommandHandler.class);

    private final CommandBus commandBus;
    private final AccountRepository accountRepository;

    public CreateAccountCommandHandler(CommandBus commandBus, AccountRepository accountRepository) {
        this.commandBus = commandBus;
        this.accountRepository = accountRepository;
    }

    @Override
    @Subscribe
    public void handle(CreateAccountCommand aCommand) {
        UUID newAccountNumber = createNewAccount(aCommand.getCreateAccountRequestId(), aCommand.getInitialBalance());
        commandBus.publish(new ConfirmNewAccountToClientCommand(aCommand.getCreateAccountRequestId(), newAccountNumber));
        accountRepository.save(new AccountCreatedEvent(aCommand.getCreateAccountRequestId(), newAccountNumber, aCommand.getInitialBalance()));
    }

    private UUID createNewAccount(UUID requestId, BigDecimal anInitialBalance) {
        LOG.debug("########### Creating a new account number for the request " + requestId.toString() + " with an initial balance of " + anInitialBalance);
        return UUID.randomUUID();
    }
}
