package com.example.publisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

@SpringBootApplication
public class Publisher {
    public static void main(String[] args) {
        SpringApplication.run(Publisher.class, args);
    }

    // Producer Example using JSON
//    @Bean
//    public MessageConverter jsonMessageConverter() {
//        return new MappingJackson2MessageConverter();
//    }
}
