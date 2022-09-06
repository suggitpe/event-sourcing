package org.suggs.sandbox.eventsourcing.bankaccount.accounts.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.events.AccountCreationRequestedEvent.anAccountCreationRequestedEventFor;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AccountRepositoryConfiguration.class)
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
