package org.suggs.sandbox.eventsourcing.bankaccount.accounts.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.events.AccountCreationRequestedEvent;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AccountRepositoryConfiguration.class)
public class AccountRepositoryTest {

    private static UUID requestId = UUID.randomUUID();
    private static BigDecimal ONE_THOUSAND = BigDecimal.valueOf(1000.00);

    @Inject
    private AccountRepository accountRepository;

    @Test
    public void savesEventsToTheRepository() {
        accountRepository.save(AccountCreationRequestedEvent.anAccountCreationRequestedEventFor(requestId, ONE_THOUSAND));
        assertThat(accountRepository.size()).isEqualTo(1);
    }


}
