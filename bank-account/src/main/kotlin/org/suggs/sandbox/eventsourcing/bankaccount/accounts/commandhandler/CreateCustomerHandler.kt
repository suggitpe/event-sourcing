package org.suggs.sandbox.eventsourcing.bankaccount.accounts.commandhandler

import com.google.common.eventbus.AllowConcurrentEvents
import com.google.common.eventbus.Subscribe
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.aggregate.CustomerAggregate
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.domain.command.CreateNewCustomer
import org.suggs.sandbox.eventsourcing.bankaccount.accounts.eventstore.EventStore

class CreateCustomerHandler(
    private val eventStore: EventStore,
    private val customerAggregate: CustomerAggregate
) : CommandHandler<CreateNewCustomer> {

    @Subscribe
    @AllowConcurrentEvents
    override fun handle(command: CreateNewCustomer) {
        eventStore.save(customerAggregate.execute(command))
    }


}