package org.suggs.sandbox.eventsourcing.bankaccount.accounts.controllers;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.web.SpringBootMockServletContext;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = MOCK)
@ContextConfiguration(classes = AccountControllerTestConfiguration.class)
public class AccountsControllerTest {

    @Inject
    private WebApplicationContext webAppCtx;

    private MockMvc mockMvc;

    @BeforeEach
    public void onSetup() {
        mockMvc = webAppContextSetup(webAppCtx).build();
    }

    @Test
    public void checkCntext() throws Exception{
        ServletContext ctx = webAppCtx.getServletContext();
        assertThat(ctx).isNotNull();
        assertThat(ctx).isInstanceOf(MockServletContext.class);
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