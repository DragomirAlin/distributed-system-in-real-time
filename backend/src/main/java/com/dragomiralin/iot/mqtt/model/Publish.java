package com.dragomiralin.iot.mqtt.model;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class Publish {

        @NotNull
        private String topic;

        @NotNull
        private String message;

        @NotNull
        private Boolean retained;

        @NotNull
        private Integer qos;
}
