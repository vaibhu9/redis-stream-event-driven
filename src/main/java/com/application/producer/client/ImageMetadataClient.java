package com.application.producer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.application.producer.dto.ImageMetadata;

@FeignClient(name = "imageMetadatClient" ,url="https://jsonplaceholder.typicode.com")
public interface ImageMetadataClient {
    
    @GetMapping("/photos/{no}")
    ImageMetadata getImageMetadata(@PathVariable("no") int no);
}
