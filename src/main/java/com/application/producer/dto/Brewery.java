package com.application.producer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brewery {
    
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("brewery_type")
    private String breweryType;

    @JsonProperty("address_1")
    private String address;

    @JsonProperty("city")
    private String city;

    @JsonProperty("country")
    private String country;

    @JsonProperty("state")
    private String state;

    @JsonProperty("street")
    private String street;
}
