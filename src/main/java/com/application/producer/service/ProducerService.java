package com.application.producer.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.application.producer.client.BreweryClient;
import com.application.producer.client.ImageMetadataClient;
import com.application.producer.dto.Brewery;
import com.application.producer.dto.ImageMetadata;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProducerService {

    @Value("${brewery-data.stream.key}")
    private String streamKey1;
    
    @Value("${image-metadata.stream.key}")
    private String streamKey2;

    private final BreweryClient breweryClient;

    private final ImageMetadataClient imageMetadataClient;

    private final RedisTemplate<String, String> redisTemplate;

    public ProducerService(RedisTemplate<String, String> redisTemplate, BreweryClient breweryClient, ImageMetadataClient imageMetadataClient) {
        this.redisTemplate = redisTemplate;
        this.breweryClient = breweryClient;
        this.imageMetadataClient = imageMetadataClient;
    }

    @Scheduled(fixedRate = 10000)
    public void publishBreweryData() {
        List<Brewery> breweryList = breweryClient.getBreweryInfo();
        Brewery brewery = breweryList.get(0);
        ObjectRecord<String, Brewery> record = ObjectRecord.create(streamKey1, brewery);
        log.info("Publishing record for stream {}  : {}", streamKey1, brewery.toString());
        redisTemplate.opsForStream().add(record);
    }

    @Scheduled(fixedRate = 15000)
    public void publishImageMetaData() {
        int num = generateRandomNumber();
        ImageMetadata imageMetadata = imageMetadataClient.getImageMetadata(num);
        ObjectRecord<String, ImageMetadata> record = ObjectRecord.create(streamKey2, imageMetadata);
        log.info("Publishing record for stream {}  : {}", streamKey2, imageMetadata.toString());
        redisTemplate.opsForStream().add(record);
    } 

    public int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(5001);
    }

}
