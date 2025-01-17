package com.application.consumer.service;

import java.util.Map;

import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Service;

import com.application.consumer.entity.Brewery;
import com.application.consumer.repository.ConsumerRepository;

import lombok.extern.slf4j.Slf4j;

@Service("saveDataListener")
@Slf4j
public class SaveDataListner implements StreamListener<String, MapRecord<String,String,String>> {

    private final ConsumerRepository consumerRepository;

    public SaveDataListner(ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

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

        consumerRepository.save(brewery);

        log.info("Message Saved in Database.......");

    }

}
