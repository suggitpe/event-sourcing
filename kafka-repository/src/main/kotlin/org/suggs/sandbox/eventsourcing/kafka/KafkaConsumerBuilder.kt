package org.suggs.sandbox.eventsourcing.kafka

import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.ProducerConfig
import java.util.*

class KafkaConsumerBuilder {

    private val properties = Properties()

    companion object {
        fun aKafkaConsumer() = KafkaConsumerBuilder()
    }

    fun connectedTo(kafkaUrl: String): KafkaConsumerBuilder = addPropertiesAndReturn(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaUrl)
    fun knownAs(clientName: String): KafkaConsumerBuilder = addPropertiesAndReturn(ConsumerConfig.GROUP_ID_CONFIG, clientName)
    fun usingKeyDeserializer(deserializerName: String?): KafkaConsumerBuilder = addPropertiesAndReturn(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, deserializerName)
    fun usingValueDeserializer(deserializerName: String?): KafkaConsumerBuilder = addPropertiesAndReturn(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializerName)

    fun <K, V> build(): Consumer<K, V> {
        return KafkaConsumer(properties)
    }

    private fun addPropertiesAndReturn(aKey: String, aValue: String?): KafkaConsumerBuilder {
        properties[aKey] = aValue
        return this
    }
}