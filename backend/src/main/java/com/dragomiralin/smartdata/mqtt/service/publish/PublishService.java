package com.dragomiralin.smartdata.mqtt.service.publish;

import com.dragomiralin.smartdata.mqtt.config.MqttConfig;
import com.dragomiralin.smartdata.mqtt.model.PublishDTO;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PublishService {

    public void publishMessage(PublishDTO publishObject) throws MqttException {
        MqttMessage mqttMessage = new MqttMessage(publishObject.getMessage().getBytes());
        mqttMessage.setQos(publishObject.getQos());
        mqttMessage.setRetained(publishObject.getRetained());


        MqttConfig.getInstance().publish(publishObject.getTopic(), mqttMessage);
    }
}

