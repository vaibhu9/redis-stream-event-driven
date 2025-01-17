package com.application.producer.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.application.producer.dto.Brewery;

@FeignClient(name = "breweryClient" ,url="https://api.openbrewerydb.org")
public interface BreweryClient {

    @GetMapping("/v1/breweries/random")
    List<Brewery> getBreweryInfo();
}
