package com.mongokafkaexample.controller;

import com.mongokafkaexample.listener.KafkaConsumer;
import com.mongokafkaexample.model.User;
import com.mongokafkaexample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="users")
public class MyRestController {

    @Autowired
    private KafkaConsumer consumer;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="/findUserByOffset/{offset}")
    public User getUserByOffset(@PathVariable Long offset) {
        return consumer.getUserByOffset(offset);
    }

    @GetMapping(path="/findUserByName/{name}")
    public User getUserByName(@PathVariable String name) {
        return userRepository.findByName(name);
    }

    @GetMapping(path = "/findUsersInRange/{lowerOffset}/{upperOffset}")
    public List<User> getUsersByOffsetRange(@PathVariable Long lowerOffset, @PathVariable Long upperOffset) {
        return consumer.getUserByOffsetRange(lowerOffset, upperOffset);
    }

}
