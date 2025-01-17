package com.application.consumer.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;

@Configuration
public class RedisConfig {

    @Value("${brewery-data.stream.key}")
    private String streamKey;

    @Bean
    public Subscription logDataSubscription(RedisConnectionFactory connectionFactory, @Qualifier("logDataListener") StreamListener<String, MapRecord<String,String,String>> logDataListener) {
        StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String, MapRecord<String,String,String>> options = StreamMessageListenerContainer
                .StreamMessageListenerContainerOptions
                .builder()
                .pollTimeout(Duration.ZERO)
                .build();

        StreamMessageListenerContainer<String, MapRecord<String,String,String>> container = StreamMessageListenerContainer
                .create(connectionFactory, options);

        Subscription subscription = container.receive(
                StreamOffset.fromStart(streamKey),
                logDataListener
        );

        container.start();
        return subscription;
    }


    @Bean
    public Subscription saveDataSubscription(RedisConnectionFactory connectionFactory, @Qualifier("saveDataListener") StreamListener<String, MapRecord<String,String,String>> saveDataListener) {
        StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String, MapRecord<String,String,String>> options = StreamMessageListenerContainer
                .StreamMessageListenerContainerOptions
                .builder()
                .pollTimeout(Duration.ZERO)
                .build();

        StreamMessageListenerContainer<String, MapRecord<String,String,String>> container = StreamMessageListenerContainer
                .create(connectionFactory, options);

        Subscription subscription = container.receive(
                StreamOffset.fromStart(streamKey),
                saveDataListener
        );

        container.start();
        return subscription;
    }

}
