package com.dragomiralin.iot.sensor.mapper;

import com.dragomiralin.iot.sensor.domain.Sensor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class JsonMapper {

    private static Sensor fromJSONObject(String jsonValue) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        Sensor sensor = null;
        try {
            sensor = objectMapper.readValue(jsonValue, Sensor.class);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception for Json to Object conversion, more details : {}", e);
        }
        return sensor;
    }
}
