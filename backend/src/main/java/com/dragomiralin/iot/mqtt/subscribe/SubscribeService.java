package com.dragomiralin.iot.mqtt.subscribe;

import com.dragomiralin.iot.mqtt.config.MqttConfig;
import com.dragomiralin.iot.mqtt.model.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SubscribeService {

    public void subscribeChannel(String topicName) throws MqttException {
        MqttConfig.getInstance().subscribeWithResponse(topicName, (s, mqttMessage) -> {
            Subscribe mqttSubscribeModel = new Subscribe();
            mqttSubscribeModel.setMessage(new String(mqttMessage.getPayload()));
            log.info("Message from MQTT: " +mqttSubscribeModel.getMessage() + ", topic: " + topicName);
        });
    }

    public void unsubscribeChannel(String topicName) throws MqttException {
        MqttConfig.getInstance().subscribeWithResponse(topicName);
        log.info("Unsubscibe to: " + topicName);
    }

}
