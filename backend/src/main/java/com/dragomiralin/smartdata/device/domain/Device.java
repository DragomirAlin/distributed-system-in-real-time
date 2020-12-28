package com.dragomiralin.smartdata.device.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Device {

    private String moduleID;
    private double temperature;
    private double humidity;
}
