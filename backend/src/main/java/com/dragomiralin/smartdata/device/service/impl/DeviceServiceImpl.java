package com.dragomiralin.smartdata.device.service.impl;

import com.dragomiralin.smartdata.device.domain.Device;
import com.dragomiralin.smartdata.device.mapper.JsonMapper;
import com.dragomiralin.smartdata.device.service.DeviceService;
import com.dragomiralin.smartdata.device.service.TopicService;
import com.dragomiralin.smartdata.mqtt.service.subscribe.SubscribeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {
    private final SubscribeService subscribeService;
    private final TopicService topicService;

    @PostConstruct
    public void subscribeAllTopics() {
        topicService.findAllTopics().stream().forEach(topic -> {
            try {
                subscribeService.subscribeChannel(topic.getTopic());
            } catch (MqttException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void subscribeTopic(String topic) throws MqttException {
        subscribeService.subscribeChannel(topic);
        topicService.saveTopic(topic);
    }

    @Override
    public void unsubscribeTopic(String topic) throws MqttException {
        subscribeService.unsubscribeChannel(topic);
        topicService.deleteTopic(topic);
    }

    @Override
    public Device getPayload() {
        var payload = subscribeService.getPayload();

        return JsonMapper.fromJSONObject(payload);
    }

}
