package com.ashwin.kafka.listener;

import com.ashwin.kafka.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@KafkaListener(topics = "#{'${spring.kafka.incoming.topic}'.split(',')}", groupId = "group_id")
public class KafkaConsumer {

    @KafkaHandler
    public void consume(@Payload String message, @Header(KafkaHeaders.OFFSET) String offset) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            User user = mapper.readValue(message, User.class);
            System.out.println("Consumed message : " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
