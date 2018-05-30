package org.suggs.sandbox.eventsourcing.bankaccount.accounts.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = MOCK)
@ContextConfiguration(classes = AccountControllerTestConfiguration.class)
public class AccountsControllerTest {

    @Inject
    private WebApplicationContext webAppCtx;

    private MockMvc mockMvc;

    @Before
    public void onSetup() {
        mockMvc = webAppContextSetup(webAppCtx).build();
    }

    @Test
    public void createsACreateAccountRequest() throws Exception {
        mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"initialBalance\":\"500\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}