package com.kafka.newAvroKafka.Service

import com.mayank.Student
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Service
import org.springframework.util.concurrent.ListenableFuture
import org.springframework.util.concurrent.ListenableFutureCallback

@Service
class ProducerService {

    private val logger: Logger = LoggerFactory.getLogger(ProducerService::class.java)
    var topic : String = "TestTopic"

    @Autowired
    private val kafkaTemplate : KafkaTemplate<String,Student>? = null

    fun sendMessage(message:Student):String
    {
//        logger.info()
        var future : ListenableFuture<SendResult<String,Student>> = kafkaTemplate?.send(topic,message) as ListenableFuture<SendResult<String, Student>>
        return "Success send $message to the topic $topic"
    }
}