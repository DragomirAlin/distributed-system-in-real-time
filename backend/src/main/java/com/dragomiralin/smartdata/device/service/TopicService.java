package com.dragomiralin.smartdata.device.service;

import com.dragomiralin.smartdata.device.domain.Topic;

import java.util.List;

public interface TopicService {

    void saveTopic(String topic);
    void deleteTopic(String topic);
    List<Topic> findAllTopics();
    Topic findTopic(String topic);
}
