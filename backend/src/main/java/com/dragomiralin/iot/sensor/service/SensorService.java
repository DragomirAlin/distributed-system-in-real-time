package com.dragomiralin.iot.sensor.service;

import com.dragomiralin.iot.sensor.domain.Sensor;
import org.eclipse.paho.client.mqttv3.MqttException;

public interface SensorService {

    void setTopic(String nameTopic) throws MqttException;
    Sensor getData();
}
