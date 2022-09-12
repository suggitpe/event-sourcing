package org.suggs.sandbox.eventsourcing.bankaccount.accounts.controllers

import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.MediaType
import org.springframework.mock.web.MockServletContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import javax.inject.Inject

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@ContextConfiguration(classes = [AccountsControllerConfig::class])
@EnableAutoConfiguration
@ComponentScan
class AccountsControllerTest {

    @Inject
    private lateinit var webAppCtx: WebApplicationContext

    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun onSetup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppCtx).build()
    }

    @Test
    fun `check web context`() {
        val ctx = webAppCtx.servletContext
        ctx shouldNotBe null
        ctx.shouldBeInstanceOf<MockServletContext>()
    }

    @Test
    fun `creates a create account request`() {
        mockMvc.perform(
            post("/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"initialBalance\":\"500\", \"customerId\":\"840f7419-bbba-47b2-80ad-4f8afc16d8f6\"}".trimMargin())
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk)
    }
}