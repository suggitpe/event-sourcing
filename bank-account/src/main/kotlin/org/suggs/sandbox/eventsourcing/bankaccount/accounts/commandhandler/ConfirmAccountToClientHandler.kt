package org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandhandler

import com.google.common.eventbus.AllowConcurrentEvents
import com.google.common.eventbus.Subscribe
import org.slf4j.LoggerFactory
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command.ConfirmAccountToClient
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.AccountConfirmedToClient.Companion.accountConfirmedToClient
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.repository.EventRepository
import java.util.*

class ConfirmAccountToClientHandler(private val eventRepo: EventRepository) : CommandHandler<ConfirmAccountToClient> {

    companion object{
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    @Subscribe
    @AllowConcurrentEvents
    override fun handle(command: ConfirmAccountToClient) {
        confirmAccountToClient(command.accountNumber)
        eventRepo.save(accountConfirmedToClient(command.requestId, command.accountNumber))
    }

    private fun confirmAccountToClient(accountNumber: UUID) {
        log.info("Emailing client to inform that we have created a new account [$accountNumber] for them")
    }
}