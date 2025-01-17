package com.application.consumer.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Brewery {
    
    @Id
    private String entryId;
    
    private String id;

    private String name;

    private String breweryType;

    private String address;

    private String city;

    private String country;

    private String state;

    private String street;
}
