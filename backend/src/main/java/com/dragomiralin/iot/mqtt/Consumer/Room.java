package com.dragomiralin.iot.mqtt.Consumer;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Room {

    @Autowired
    private Consumer consumer;


    public void followTopic() throws MqttException, InterruptedException {
        consumer.subscribe("alin");
    }
}
