# Event Sourcing and Stream Processing

## Sources
 - Linkedin architecture.  very simple write process in changing the data of a profile.  Updates a full text index for searching of profiles.  It can also then update the linkedin connections for others (and alert as necessary).
 - Google analytics
 - Twitter ... tweets are written and then uses the fanout service to build peoples timelines ahead of time.  The fail whale was when they were using SQL.
 - Facebook like process.  This requires some clever caching process that will allow you to connect all the likes for all the streams of data and aggregate it all together.

## Concrete implementations
 - Apache Kafka for publish/subscribe messaging
 - Apache Kafka Streams for processing messages from Kafka queues
 - Apache Samza for Kafka client libraries.  See also Storm, Spark, Flink
 - See comparison information in the [Samza documentation](http://samza.apache.org/learn/documentation/0.9/comparisons/introduction.html) 

## Stream Processing & Event Sourcing
 - Structure data as a stream of events and proess them in order
 - Event Sourcing is the descrption of immutable events that describe events in the past
 - How to store the data
     - store the raw event data to attain maximum flexibility for analysis of the data --> very good for offline analysis of the data
     - store aggregated data when you need to make near real-time decisions
 - Event sourcing is concerned with how we structure data in databases.  Everything is stored as an immutable event. No state mutation.
 - Bring Event Sourcing and Stream Processing together for a natural sweet spot.
 - Raw events are in a form ideal for writing, the aggregated data set is in an ideal state for reading.  This is where CQRS (Command Query Responsibility Segregation) comes in.
 - Taking a large number of immutable facts and then denormalise/aggregate the data is the key to this area.  Data comes in, something reads those feeds of data and updates an aggregate position in a cache somewhere that is then read on-demand (efficiently).
 - If you needed to you could rerun all the events in the store and deterministically re-create the same end state.  Storing the aggregate positions separately from the events means that you have efficient writes and reads.

## Why adopt Event Sourced architectures?
 - Loose coupling between writer and reader processes
 - Read/wrire performance ... ES breaks the assuption that read/write have to share the same schema
 - Scalability of individual components (see loose coupling point)
 - Flexibility and agility ... projection of data is simpler if the raw events never change
 - Error scenarios are simpler to deal with because the source of truth is immutable ... event replay is simpler

### Aggregate Summaries
 - you can have the source update the aggregates but this is inefficient
 - using an event stream allows you to have multiple consumers of the data (eg an aggregation and a raw store)

## Log Streams
 - Append-only sequence of events that is in time order.
 - Problem statement: written data events need to be replicated to many projection mechanisms (caches, indexes, reports, GUIs etc).  How do we ensure that the data remains intact and synchronized?
    - Dual Writes: bad ideas as it exposes race conditions and inconsistent data across the data sources.  Writes across data sources need to be atomic (ACID).
    - Write from an ordered stream of events with multiple subscribers means you guarantee the application of the ordered data
 - A Log is an append-only persistent data structure.

## Log examples
 - Write Ahead Logs (WAL) in database engines (the transaction log)
 - Log Strustured Storage (eg Cassandra)
 - Database Replication ... typically a derivative of the Write Ahead Log (either a copy or the same one)
 - Distributed Consensus (eg Raft and ZooKeeper/Zab)
 - 





