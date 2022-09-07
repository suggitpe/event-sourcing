# Bank Account Event Sourcing example

## Main components
 - domain objects are the commands and events
 - commands are sent to the command bus
 - commandHandlers are registered withe the command bus observing commands of type T
 - command handlers process the commands, publish new commands and save resulting events to the event repository


