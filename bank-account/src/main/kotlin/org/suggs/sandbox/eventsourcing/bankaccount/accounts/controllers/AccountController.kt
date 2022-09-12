package org.suggs.sandbox.eventsourcing.bankaccount.accounts.controllers

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.bus.CommandBus
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.controllers.RequestResponse.aResponseForRequestWithIdOf
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command.CreateAccount
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.repository.EventRepository
import java.util.*
import javax.inject.Inject

@RestController
class AccountController {

    @Inject
    private lateinit var eventRepo: EventRepository

    @Inject
    private lateinit var commandBus: CommandBus

    @PostMapping("/account")
    fun createAccount(@Validated @RequestBody request: CreateAccountRequest): RequestResponse {
        var requestId = UUID.randomUUID()
        commandBus.publish(CreateAccount(requestId, request.initialBalance))
        return aResponseForRequestWithIdOf(requestId)
    }

}