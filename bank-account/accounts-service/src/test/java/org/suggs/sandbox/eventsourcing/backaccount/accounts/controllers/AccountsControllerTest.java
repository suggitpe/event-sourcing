package org.suggs.sandbox.eventsourcing.backaccount.accounts.controllers;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class AccountsControllerTest {

    @Autowired
    private WebApplicationContext webAppCtx;

    private MockMvc mockMvc;

    @Before
    public void onSetup() {
        mockMvc = webAppContextSetup(webAppCtx).build();
    }

    @Test
    public void openAnAccountWithAnInitialBalance() {
    }

}