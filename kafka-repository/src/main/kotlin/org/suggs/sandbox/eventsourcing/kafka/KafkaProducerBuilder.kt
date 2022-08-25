package org.suggs.sandbox.eventsourcing.kafka

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerConfig
import java.util.*

class KafkaProducerBuilder {

    private val properties = Properties()

    companion object {
        fun aKafkaProducer() = KafkaProducerBuilder()
    }

    fun connectedTo(kafkaUrl: String) = addPropertiesAndReturn(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaUrl)
    fun knownAs(clientName: String) = addPropertiesAndReturn(ProducerConfig.CLIENT_ID_CONFIG, clientName)
    fun usingKeySerializer(serializerName: String?) = addPropertiesAndReturn(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, serializerName)
    fun usingValueSerializer(serializerName: String?) = addPropertiesAndReturn(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, serializerName)

    fun <K, V> build(): Producer<K, V> {
        return KafkaProducer(properties)
    }

    private fun addPropertiesAndReturn(aKey: String, aValue: String?): KafkaProducerBuilder {
        properties[aKey] = aValue
        return this
    }
}