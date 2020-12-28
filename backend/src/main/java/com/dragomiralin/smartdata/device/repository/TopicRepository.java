package com.dragomiralin.smartdata.device.repository;

import com.dragomiralin.smartdata.device.domain.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicRepository extends MongoRepository<Topic, String> {

    Optional<Topic> findByTopic(String topic);
}
