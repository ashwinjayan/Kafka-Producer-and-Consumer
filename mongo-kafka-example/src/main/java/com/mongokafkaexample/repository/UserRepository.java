package com.mongokafkaexample.repository;

import com.mongokafkaexample.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByOffset(Long offset);
    User findByName(String name);

    @Query("{'offset' : { $gte: ?0, $lte: ?1 }}")
    List<User> findByOffsetRange(Long lowerOffset, Long upperOffset);
}
