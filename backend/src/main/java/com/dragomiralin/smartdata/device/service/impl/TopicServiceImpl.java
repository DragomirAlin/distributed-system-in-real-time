package com.dragomiralin.smartdata.device.service.impl;

import com.dragomiralin.smartdata.device.domain.Topic;
import com.dragomiralin.smartdata.device.repository.TopicRepository;
import com.dragomiralin.smartdata.device.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {
    public final TopicRepository topicRepository;

    @Override
    public void saveTopic(String topic) {
        Optional<Topic> top = topicRepository.findByTopic(topic);

        Topic newTopic = new Topic();
        newTopic.setTopic(topic);
        topicRepository.save(newTopic);

    }

    @Override
    public void deleteTopic(String topic) {
        Topic top = findTopic(topic);
        topicRepository.delete(top);
    }

    @Override
    public List<Topic> findAllTopics() {
        return topicRepository.findAll();
    }

    @Override
    public Topic findTopic(String topic) {
        return topicRepository.findByTopic(topic).orElseThrow();
    }
}
