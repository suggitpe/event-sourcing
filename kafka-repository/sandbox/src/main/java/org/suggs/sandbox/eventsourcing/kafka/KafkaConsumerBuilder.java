package org.suggs.sandbox.eventsourcing.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Properties;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;
import static org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG;

public class KafkaConsumerBuilder {

    private Properties properties;

    private KafkaConsumerBuilder() {
        properties = new Properties();
    }

    public static KafkaConsumerBuilder aKafkaConsumer() {
        return new KafkaConsumerBuilder();
    }

    public KafkaConsumerBuilder connectedTo(String kafkaUrl) {
        properties.put(BOOTSTRAP_SERVERS_CONFIG, kafkaUrl);
        return this;
    }

    public KafkaConsumerBuilder knownAs(String clientName) {
        properties.put(GROUP_ID_CONFIG, clientName);
        return this;
    }

    public KafkaConsumerBuilder usingKeyDeserializer(String deserializerName) {
        properties.put(KEY_DESERIALIZER_CLASS_CONFIG, deserializerName);
        return this;
    }

    public KafkaConsumerBuilder usingValueDeserializer(String deserializerName) {
        properties.put(VALUE_DESERIALIZER_CLASS_CONFIG, deserializerName);
        return this;
    }

    public <K, V> Consumer<K, V> build() {
        return new KafkaConsumer<>(properties);
    }
}
