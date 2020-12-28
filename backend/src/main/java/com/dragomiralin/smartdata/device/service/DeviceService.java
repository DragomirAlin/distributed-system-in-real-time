package com.dragomiralin.smartdata.device.service;

import com.dragomiralin.smartdata.device.domain.Device;
import org.eclipse.paho.client.mqttv3.MqttException;

public interface DeviceService {

    void subscribeTopic(String topic) throws MqttException;
    void unsubscribeTopic(String topic) throws MqttException;
    Device getPayload();
}
