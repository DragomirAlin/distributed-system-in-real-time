package com.dragomiralin.iot.mqtt.Consumer;

import com.dragomiralin.iot.mqtt.Config.MqttConfig;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    private MqttConfig mqttConfig;

    public void subscribe(final String topic) throws MqttException, InterruptedException {
        System.out.println("Messages received:");

        mqttConfig.getInstance().subscribeWithResponse(topic, (tpic, msg) -> {
            System.out.println(msg.getId() + " -> " + new String(msg.getPayload()));
        });
    }

}
