package com.dragomiralin.iot.mqtt.Producer;

import com.dragomiralin.iot.mqtt.Config.MqttConfig;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    private MqttConfig mqttConfig;


    private MqttClient mqttClient;

    public void publish(final String topic, final String payload, int qos, boolean retained) throws MqttPersistenceException, MqttException {
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setPayload(payload.getBytes());
        mqttMessage.setQos(qos);
        mqttMessage.setRetained(retained);
        mqttConfig.getInstance().publish(topic, mqttMessage);


        mqttClient.disconnect();
    }
}
