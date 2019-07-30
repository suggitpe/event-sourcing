package org.suggs.sandbox.eventsourcing.kafka

import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import org.apache.kafka.common.serialization.LongDeserializer
import org.apache.kafka.common.serialization.LongSerializer
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.slf4j.LoggerFactory.getLogger
import org.suggs.sandbox.eventsourcing.kafka.KafkaConsumerBuilder.Companion.aKafkaConsumer
import org.suggs.sandbox.eventsourcing.kafka.KafkaProducerBuilder.Companion.aKafkaProducer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class KafkaConnectivity {

    companion object {
        private const val KAFKA_URL = "localhost:9092"
        private const val TOPIC = "test-topic"
        private val LOG = getLogger(this::class.java)
    }

    private lateinit var producer: Producer<Long, String>
    private lateinit var consumer: Consumer<Long, String>
    private var now = System.currentTimeMillis()

    @BeforeEach
    fun `setup`() {
        producer = aKafkaProducer()
                .connectedTo(KAFKA_URL)
                .knownAs("TestProducer")
                .usingKeySerializer(LongSerializer::class.qualifiedName)
                .usingValueSerializer(StringSerializer::class.qualifiedName)
                .build()

        consumer = aKafkaConsumer()
                .connectedTo(KAFKA_URL)
                .knownAs("TestConsumer")
                .usingKeyDeserializer(LongDeserializer::class.qualifiedName)
                .usingValueDeserializer(StringDeserializer::class.qualifiedName)
                .build()
    }

    @AfterEach
    fun `tear down`() {
        producer.flush()
        producer.close()
        consumer.close()
    }

    @Test
    fun `connects to kafka and writes a message synchronously`() {
        for (index in now until now + 10) {
            val record = ProducerRecord(TOPIC, index, "Foo $index")
            val metadata = producer.send(record).get()
        }
    }

    @Test
    fun `connects to kafka and writes a message asynchronously`() {

        fun manageResponse(metadata: RecordMetadata?, exception: Exception, latch: CountDownLatch) {
            if (metadata != null) {
                LOG.debug(metadata.toString())
            }
            latch.countDown()
        }

        val latch = CountDownLatch(10)
        for (index in now until now + 10) {
            val record = ProducerRecord(TOPIC, index, "Foo $index")
            producer.send(record) { metadata, exception -> manageResponse(metadata, exception, latch) }
        }
        latch.await(25, TimeUnit.SECONDS)
    }

    @Test
    fun `connects to kafka and consumes all messages`() {
        consumer.subscribe(listOf(TOPIC))
        var noRecordCount = 0
        val giveUp = 1000
        while (true) {
            LOG.debug("Polling for records")
            val records = consumer.poll(1000)

            LOG.debug("Found [" + records.count() + "] records")
            if (records.count() == 0) {
                noRecordCount++
                if (noRecordCount > giveUp)
                    break
                else
                    continue
            }

            records.forEach { record -> LOG.debug(record.toString()) }
            consumer.commitAsync()
        }
        LOG.debug("Read all messages")
    }


}