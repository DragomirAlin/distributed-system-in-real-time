package com.dragomiralin.smartdata.mqtt.service.subscribe;

import com.dragomiralin.smartdata.mqtt.config.MqttConfig;
import com.dragomiralin.smartdata.mqtt.model.SubscribeDTO;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SubscribeService {
    private String payload;

    public void subscribeChannel(String topicName) throws MqttException {
        MqttConfig.getInstance().subscribeWithResponse(topicName, (s, mqttMessage) -> {
            SubscribeDTO mqttSubscribeModel = new SubscribeDTO();
            mqttSubscribeModel.setMessage(new String(mqttMessage.getPayload()));
            this.payload = mqttSubscribeModel.getMessage();
            log.info("Message from MQTT: " +mqttSubscribeModel.getMessage() + ", topic: " + topicName);
        });
    }

    public String getPayload(){
        return this.payload;
    }

    public void unsubscribeChannel(String topicName) throws MqttException {
        MqttConfig.getInstance().subscribeWithResponse(topicName);
        log.info("Unsubscibe to: " + topicName);
    }

}
