package com.kafka.newAvroKafka.Config

import com.mayank.Student
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import java.sql.Struct

@EnableKafka
@Configuration
class ConsumerConfiguration {

    private val brokers:String = "52.66.148.237:9092"
    private val groupId : String = "group_id"

    @Bean
    fun consumerFactory():ConsumerFactory<String,Student>
    {
        var props : HashMap<String,Any> = HashMap()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = brokers
        props[ConsumerConfig.GROUP_ID_CONFIG] = groupId
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = AvroDeserializer::class.java

        return DefaultKafkaConsumerFactory(props,StringDeserializer(),AvroDeserializer(Student::class.java))
    }

    @Bean
    fun kafkaListenerContainerFactory() : ConcurrentKafkaListenerContainerFactory<String,Student>
    {
        var factory : ConcurrentKafkaListenerContainerFactory<String,Student> = ConcurrentKafkaListenerContainerFactory()
        factory.consumerFactory = consumerFactory()
        return factory
    }
}