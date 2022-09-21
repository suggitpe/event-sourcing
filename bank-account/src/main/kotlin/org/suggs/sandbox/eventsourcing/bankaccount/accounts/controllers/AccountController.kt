package org.suggs.sandbox.eventsourcing.bankaccount.accounts.controllers

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandbus.CommandBus
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command.CreateNewAccount
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.eventstore.EventStore
import java.util.*
import javax.inject.Inject

@RestController
class AccountController {

    @Inject
    private lateinit var eventRepo: EventStore

    @Inject
    private lateinit var commandBus: CommandBus

    @PostMapping("/account")
    fun createAccount(@Validated @RequestBody request: CreateAccountRequest): RequestResponse {
        var requestId = UUID.randomUUID()
        commandBus.publish(CreateNewAccount(requestId, request.customerId, request.initialBalance))
        return RequestResponse(requestId)
    }

}