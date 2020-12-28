package com.dragomiralin.smartdata.mqtt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class SubscribeDTO {

    private String message;
    private Integer qos;
    private Integer id;

}
