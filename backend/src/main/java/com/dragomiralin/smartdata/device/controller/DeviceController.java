package com.dragomiralin.smartdata.device.controller;

import com.dragomiralin.smartdata.device.domain.Device;
import com.dragomiralin.smartdata.device.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/mqtt")
@RequiredArgsConstructor
public class DeviceController {
    private final DeviceService deviceService;

    @PostMapping(value = "/subscribe/{topicName}")
    public void subscribeMessage(@PathVariable("topicName") String topicName) throws MqttException {
        deviceService.subscribeTopic(topicName);
    }

    @PostMapping(value = "/unsubscribe/{topicName}")
    public void unsubscribeMessage(@PathVariable("topicName") String topicName) throws MqttException {
        deviceService.unsubscribeTopic(topicName);
    }

    @GetMapping(value = "/data")
    public Device getPayload() {
        return deviceService.getPayload();
    }
}
