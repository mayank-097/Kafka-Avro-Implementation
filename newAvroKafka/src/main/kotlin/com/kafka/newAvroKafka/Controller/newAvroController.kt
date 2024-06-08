package com.kafka.newAvroKafka.Controller

import com.kafka.newAvroKafka.Service.ProducerService
import com.mayank.Student
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/Avro")
class newAvroController {

    @Autowired
    private val producerService : ProducerService? = null

    @PostMapping("/student")
    fun kafkaMessage(@RequestBody student: Student) : String?
    {
        return producerService?.sendMessage(student)
    }
}