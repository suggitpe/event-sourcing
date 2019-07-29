package org.suggs.sandbox.eventsourcing.bankaccount.accounts;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.bus.CommandBus;
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandhandler.CommandHandlerConfiguration;
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.repository.AccountRepository;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.UUID;

import static org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.commands.CreateAccountCommand.aCreateAccountCommandWith;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CommandHandlerConfiguration.class})
public class CreateNewAccountTest {

    private static final Logger LOG = LoggerFactory.getLogger(CreateNewAccountTest.class);
    private static final UUID REQUEST_ID = UUID.randomUUID();
    private static final BigDecimal INITIAL_BALANCE = BigDecimal.valueOf(100d);

    @Inject
    private CommandBus commandBus;
    @Inject
    private AccountRepository accountRepository;

    @Test
    public void createsANewAccountWhenRequested() {
        commandBus.publish(aCreateAccountCommandWith(REQUEST_ID, INITIAL_BALANCE));
        LOG.debug(accountRepository.toString());
    }

}
