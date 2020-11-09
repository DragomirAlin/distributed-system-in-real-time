package com.dragomiralin.iot.mqtt.model;

import lombok.Data;

@Data
public class Subscribe {

    private String message;
    private Integer qos;
    private Integer id;

}
