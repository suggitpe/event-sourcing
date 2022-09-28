package org.suggs.sandbox.eventsourcing.bankaccount.accounts.aggregate

import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command.CreateNewCustomer
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.event.Event

interface CustomerAggregate : Aggregate {

    fun execute(command: CreateNewCustomer): Event

}