package com.mongokafkaexample.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongokafkaexample.model.User;
import com.mongokafkaexample.repository.UserRepository;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaConsumer {

    @Autowired
    private UserRepository userRepository;

    /*@KafkaListener(topics = "Kafka_Example_String", groupId = "group_string")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }*/


    //Without using the configuration class and setting the deserialization properties in application.yaml
    /*@KafkaListener(topics = "Kafka_Example", groupId = "group_id")
    public void consume(@Payload String message, @Header(KafkaHeaders.OFFSET) Long offset) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(message, User.class);
        System.out.println("Consumed message : " + message);
        user.setOffset(offset);
        userRepository.insert(user);
    }*/

    @KafkaListener(topics = "#{'${spring.kafka.incoming.topic}'.split(',')}", groupId = "group_id", containerFactory = "userKafkaListenerContainerFactory")
    public void consume(@Payload User user, @Header(KafkaHeaders.OFFSET) Long offset) {
        System.out.println("Consumed message : " + user.toString());
        user.setOffset(offset);
        userRepository.insert(user);
    }

    public User getUserByOffset(Long offset) {
        return userRepository.findByOffset(offset);
    }

    public List<User> getUserByOffsetRange(Long lowerOffset, Long upperOffset) {
        return  userRepository.findByOffsetRange(lowerOffset, upperOffset);
    }
}
