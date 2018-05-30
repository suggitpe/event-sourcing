package org.suggs.sandbox.eventsourcing.bankaccount.accounts;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=DeleteMeTest.class, initializers = ConfigFileApplicationContextInitializer.class)
public class DeleteMeTest {

    private static final Logger LOG = LoggerFactory.getLogger(DeleteMeTest.class);

    @Bean
    public DeleteMe createDeleteMe(){
        return new DeleteMe();
    }

    @Inject
    private DeleteMe deleteMe;

    @Test
    public void fooBarBaz() {
        LOG.debug("Log for debugging");
        assertThat(deleteMe.getBar()).isEqualTo("foobarvalue");
        assertThat(deleteMe.getBaz()).isEqualTo("foobazvalue");
    }

}
