package com.ashwin.kafkaproducer.producer;

import com.ashwin.kafkaproducer.Exception.UnreachableProducer;
import com.ashwin.kafkaproducer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class Producer {

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    public void publish(String topic, User user) {
        try {
            kafkaTemplate.send(topic, user);
        } catch (Exception e) {
            throw new UnreachableProducer("Producer not found.");
        }
    }
}
