# Bank Account Event Sourcing example

## Main components
 - domain objects are the commands and events
 - commands are sent to the command bus
 - commandHandlers are registered with the command bus observing commands of type T
 - command handlers facilitate the reading of commands, allocation to aggregates and then save down the resulting events
 - aggregates contain the processing logic


