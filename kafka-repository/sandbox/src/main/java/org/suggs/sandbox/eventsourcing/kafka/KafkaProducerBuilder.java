package org.suggs.sandbox.eventsourcing.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import java.util.Properties;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

public class KafkaProducerBuilder {

    private Properties properties;

    private KafkaProducerBuilder() {
        properties = new Properties();
    }

    public static KafkaProducerBuilder aKafkaProducer() {
        return new KafkaProducerBuilder();
    }

    public KafkaProducerBuilder connectedTo(String kafkaUrl) {
        properties.put(BOOTSTRAP_SERVERS_CONFIG, kafkaUrl);
        return this;
    }

    public KafkaProducerBuilder knownAs(String clientName) {
        properties.put(CLIENT_ID_CONFIG, clientName);
        return this;
    }

    public KafkaProducerBuilder usingKeySerializer(String serializerName) {
        properties.put(KEY_SERIALIZER_CLASS_CONFIG, serializerName);
        return this;
    }


    public KafkaProducerBuilder usingValueSerializer(String serializerName) {
        properties.put(VALUE_SERIALIZER_CLASS_CONFIG, serializerName);
        return this;
    }


    public <K, V> Producer<K, V> build() {
        return new KafkaProducer<>(properties);
    }

}
