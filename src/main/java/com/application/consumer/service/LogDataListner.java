package com.application.consumer.service;

import java.util.Map;

import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Service;

import com.application.consumer.entity.Brewery;

import lombok.extern.slf4j.Slf4j;

@Service("logDataListener")
@Slf4j
public class LogDataListner implements StreamListener<String, MapRecord<String,String,String>> {

    @Override
    public void onMessage(MapRecord<String,String,String> message) {
        String key = message.getId().getValue();
        
        Map<String, String> valueMap = message.getValue();

        Brewery brewery = Brewery.builder()
                .entryId(key)
                .id(valueMap.get("id"))
                .name(valueMap.get("name"))
                .breweryType(valueMap.get("breweryType"))
                .address(valueMap.get("address"))
                .city(valueMap.get("city"))
                .country(valueMap.get("country"))
                .state(valueMap.get("state"))
                .street(valueMap.get("street"))
                .build();

        log.info("Message Consumed of Key : {} and Value : {}", key, brewery.toString());

    }

}
