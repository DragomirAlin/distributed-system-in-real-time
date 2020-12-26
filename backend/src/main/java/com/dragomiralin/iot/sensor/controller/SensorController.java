package com.dragomiralin.iot.sensor.controller;

import com.dragomiralin.iot.sensor.domain.Sensor;
import com.dragomiralin.iot.sensor.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/mqtt")
@RequiredArgsConstructor
public class SensorController {

    private final SensorService sensorService;

    @PostMapping(value = "/subscribe/{topicName}")
    public void subscribeMessage(@PathVariable("topicName") String topicName) throws MqttException {
        sensorService.subscribeTopic(topicName);
    }

    @PostMapping(value = "/unsubscribe/{topicName}")
    public void unsubscribeMessage(@PathVariable("topicName") String topicName) throws MqttException {
        sensorService.unsubscribeTopic(topicName);
    }

    @GetMapping(value = "/data")
    public Sensor getData(){
        return sensorService.getData();
    }

}