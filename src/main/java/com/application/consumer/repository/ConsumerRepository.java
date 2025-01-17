package com.application.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.consumer.entity.Brewery;

public interface ConsumerRepository extends JpaRepository<Brewery, String> {

}
