package com.dragomiralin.smartdata.mqtt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PublishDTO {

    @NotNull
    private String topic;
    @NotNull
    private String message;
    @NotNull
    private Boolean retained;
    @NotNull
    private Integer qos;
}
