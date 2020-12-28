package com.dragomiralin.smartdata.device.mapper;


import com.dragomiralin.smartdata.device.domain.Device;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class JsonMapper {

    public static Device fromJSONObject(String jsonValue) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        Device device = null;
        try {
            device = objectMapper.readValue(jsonValue, Device.class);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception for Json to Object conversion, more details : {}", e);
        }
        return device;
    }
}
