package org.suggs.sandbox.eventsourcing.backaccount.accounts.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.suggs.sandbox.eventsourcing.backaccount.accounts.domain.AccountCreationRequestedEvent.anAccountCreationRequestedEventFor;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AccountRepositoryConfiguration.class)
public class AccountRepositoryTest {

    private static UUID requestId = UUID.randomUUID();
    private static BigDecimal ONE_THOUSAND = BigDecimal.valueOf(1000.00);

    @Inject
    private AccountRepository accountRepository;

    @Test
    public void savesEventsToTheRepository() {
        accountRepository.save(anAccountCreationRequestedEventFor(requestId, ONE_THOUSAND));
        assertThat(accountRepository.size()).isEqualTo(1);
    }


}
