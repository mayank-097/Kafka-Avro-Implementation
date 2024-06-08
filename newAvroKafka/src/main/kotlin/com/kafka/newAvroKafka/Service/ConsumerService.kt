package com.kafka.newAvroKafka.Service

import com.mayank.Student
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class ConsumerService {

    private val logger: Logger = LoggerFactory.getLogger(ConsumerService::class.java)

    @KafkaListener(topics=["TestTopic"], groupId = "group_id")
    fun listen ( message : Student)
    {
        println("Received Message $message")
    }
}