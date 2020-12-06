package com.dragomiralin.iot.sensor.service;

import com.dragomiralin.iot.sensor.domain.Sensor;
import org.eclipse.paho.client.mqttv3.MqttException;

public interface SensorService {

    void subscribeTopic(String nameTopic) throws MqttException;
    void unsubscribeTopic(String nameTopic) throws MqttException;
    Sensor getData();
}
