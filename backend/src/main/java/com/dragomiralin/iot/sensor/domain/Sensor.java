package com.dragomiralin.iot.sensor.domain;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sensor {

    private String moduleID;
    private double temperature;
    private double humidity;
}
