package com.kafka.newAvroKafka.Config

import com.kafka.newAvroKafka.Config.AvroSerializer
import com.mayank.Student
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class ProducerConfiguration {

    var broker : String = "52.66.148.237:9092"

    @Bean
    fun producerFactory() :  ProducerFactory<String,Student>
    {
        var config : HashMap<String,Any> = HashMap()
        config[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG]=broker
        config[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG]= StringSerializer::class.java
        config[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = AvroSerializer::class.java

        return DefaultKafkaProducerFactory(config)
    }

    @Bean
    fun kafkaTemplate():KafkaTemplate<String,Student>
    {
        return KafkaTemplate(producerFactory())
    }
}