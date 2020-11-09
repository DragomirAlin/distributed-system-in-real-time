package com.dragomiralin.iot.mqtt.controller;

import com.dragomiralin.iot.mqtt.model.Publish;
import com.dragomiralin.iot.mqtt.publish.PublishService;
import com.dragomiralin.iot.mqtt.subscribe.SubscribeService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/mqtt")
public class MqttController {

    @Autowired
    private PublishService publishService;

    @Autowired
    private SubscribeService subscribeService;

    @PostMapping("/publish")
    public void publishMessage(@RequestBody Publish publish) throws MqttException {
        publishService.publishMessage(publish);
    }

    @GetMapping(value = "/subscribe/{topicName}")
    public void subscribeMessage(@PathVariable("topicName") String topicName) throws MqttException {
        subscribeService.subscribeChannel(topicName);
    }

    @GetMapping(value = "/unsubscribe/{topicName}")
    public void unsubscribeMessage(@PathVariable("topicName") String topicName) throws MqttException {
        subscribeService.unsubscribeChannel(topicName);
    }

}
