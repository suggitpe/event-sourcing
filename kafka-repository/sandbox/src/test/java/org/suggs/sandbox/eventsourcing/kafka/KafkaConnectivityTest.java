package org.suggs.sandbox.eventsourcing.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;

import java.util.Properties;

public class KafkaConnectivityTest {

    private static final String TOPIC = "test-topic";
    private static final String KAFKA_URL = "localhost:9092";

    @Test
    public void connectsToKafkaAndWritesAMessage(){

    }

    public Producer<Long, String> createProducer(){
        // TODO: convert into a builder
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_URL);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "TestProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return new KafkaProducer<>(props);
    }
}
