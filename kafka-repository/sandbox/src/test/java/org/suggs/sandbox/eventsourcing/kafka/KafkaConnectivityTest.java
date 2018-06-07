package org.suggs.sandbox.eventsourcing.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.suggs.sandbox.eventsourcing.kafka.KafkaConsumerBuilder.aKafkaConsumer;
import static org.suggs.sandbox.eventsourcing.kafka.KafkaProducerBuilder.aKafkaProducer;

public class KafkaConnectivityTest {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaConnectivityTest.class);
    private static final String TOPIC = "test-topic";
    private static final String KAFKA_URL = "localhost:9092";
    private Producer<Long, String> producer;
    private Consumer<Long, String> consumer;
    private long now;

    @Before
    public void onSetup() {
        producer = aKafkaProducer()
                .connectedTo(KAFKA_URL)
                .knownAs("TestProducer")
                .usingKeySerializer(LongSerializer.class.getName())
                .usingValueSerializer(StringSerializer.class.getName())
                .build();

        consumer = aKafkaConsumer()
                .connectedTo(KAFKA_URL)
                .knownAs("TestConsumer")
                .usingKeyDeserializer(LongDeserializer.class.getName())
                .usingValueDeserializer(StringDeserializer.class.getName())
                .build();

        now = System.currentTimeMillis();
    }

    @After
    public void onTearDown() {
        producer.flush();
        producer.close();
        consumer.close();
    }

    @Test
    public void connectsToKafkaAndWritesAMessageSynchronously() throws ExecutionException, InterruptedException {

        for (long index = now; index < now + 10; index++) {
            final ProducerRecord<Long, String> record = new ProducerRecord<>(TOPIC, index, "Foo " + index);
            RecordMetadata metadata = producer.send(record).get();
        }
    }

    @Test
    public void connectsToKafkaAndWritesAMessageASynchronously() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(10);
        for (long index = now; index < now + 10; index++) {
            final ProducerRecord<Long, String> record = new ProducerRecord<>(TOPIC, index, "Foo " + index);
            producer.send(record, (metadata, exception) -> manageResponse(metadata, exception, latch));
        }
        latch.await(25, TimeUnit.SECONDS);
    }

    private void manageResponse(RecordMetadata metadata, Exception exception, CountDownLatch latch) {
        if (metadata != null) {
            LOG.debug(metadata.toString());
        }
        latch.countDown();
    }

    @Test
    public void connectsToKafkaAndConsumesAllMessages() {
        consumer.subscribe(Collections.singletonList(TOPIC));
        int noRecordCount = 0;
        final int giveUp = 1000;
        while (true) {
            LOG.debug("Polling for records");
            final ConsumerRecords<Long, String> records = consumer.poll(1000);

            LOG.debug("Found [" + records.count() + "] records");
            if (records.count() == 0) {
                noRecordCount++;
                if (noRecordCount > giveUp)
                    break;
                else
                    continue;
            }

            records.forEach(record -> {
                LOG.debug(record.toString());
            });

            consumer.commitAsync();

        }
        LOG.debug("Read all messages");
    }

}
