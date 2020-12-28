package com.dragomiralin.smartdata.device.controller;

import com.dragomiralin.smartdata.device.domain.Device;
import com.dragomiralin.smartdata.device.domain.Topic;
import com.dragomiralin.smartdata.device.service.DeviceService;
import com.dragomiralin.smartdata.device.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/mqtt")
@RequiredArgsConstructor
public class DeviceController {
    private final DeviceService deviceService;
    private final TopicService topicService;

    @PostMapping(value = "/subscribe/{topicName}")
    public void subscribeMessage(@PathVariable("topicName") String topicName) throws MqttException {
        deviceService.subscribeTopic(topicName);
    }

    @PostMapping(value = "/unsubscribe/{topicName}")
    public void unsubscribeMessage(@PathVariable("topicName") String topicName) throws MqttException {
        deviceService.unsubscribeTopic(topicName);
    }

    @GetMapping(value = "/measurements/{topicName}")
    public Device getPayload() {
        return deviceService.getPayload();
    }

    @GetMapping(value = "/subscribes")
    public List<Topic> getTopics(){
        return topicService.findAllTopics();
    }
}
