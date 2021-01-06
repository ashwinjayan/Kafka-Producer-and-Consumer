package com.ashwin.kafkaproducer.controller;

import com.ashwin.kafkaproducer.model.User;
import com.ashwin.kafkaproducer.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class UserController {

    @Autowired
    private Producer producer;

    private static final String TOPIC = "Kafka_Example";

    @GetMapping("/publish/{name}")
    public String publish(@PathVariable final String name) {
        producer.publish(TOPIC, new User(name, "Tech", 20000L));
        return "Published Successfully";
    }

}
