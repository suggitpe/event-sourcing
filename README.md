# Event Sourcing

## Some notes to keep me sane

 * Commands are the requests on the aggregate
    * Foe example, "pay joe GBP 1000 from paul"
 * The aggregate is the thing that verrifies the commands are valid, executes any secondary commands and adds the events to the log store
    * For example, "as long as joe has GBP 1000 then add a paid event from joe to paul"
 * Events are the historical view of the history
    * For example, "joe paid paul GBP 1000"

### Commands
 * CreateAccount(name)
 * CreditAccountWithAmount(id, amount)
 * TransferMoniesBetweenAccounts(id_from, id_to, amount)
 * DebitAccountForAmount(id, amount)

### Events
 * AccountCreated(name, date)
 * AccountCreditedWithAmount(id, amount, date)
