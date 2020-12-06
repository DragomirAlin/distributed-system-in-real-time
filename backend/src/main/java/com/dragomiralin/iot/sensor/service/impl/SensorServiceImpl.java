package com.dragomiralin.iot.sensor.service.impl;

import com.dragomiralin.iot.mqtt.subscribe.SubscribeService;
import com.dragomiralin.iot.sensor.domain.Sensor;
import com.dragomiralin.iot.sensor.mapper.JsonMapper;
import com.dragomiralin.iot.sensor.service.SensorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService {

    private final SubscribeService subscribeService;

    @Override
    public void subscribeTopic(String nameTopic) throws MqttException {
        subscribeService.subscribeChannel(nameTopic);
    }

    @Override
    public void unsubscribeTopic(String nameTopic) throws MqttException {
        subscribeService.unsubscribeChannel(nameTopic);
    }

    @Override
    public Sensor getData() {
        var sensor = subscribeService.getPayload();

        return JsonMapper.fromJSONObject(sensor);
    }
}
