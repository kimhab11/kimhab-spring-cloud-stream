package com.example.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.config.ListenerContainerCustomizer;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.util.ErrorHandler;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.function.Function;

@SpringBootApplication
@Slf4j
@EnableBinding(Sink.class)
public class Consumer {

    public static void main(String[] args) {
        SpringApplication.run(Consumer.class, args);
    }

    /**
     * centralized error handling mechanism.
     * */
    @Bean
    public ListenerContainerCustomizer<AbstractMessageListenerContainer> customizer() {
        return (container, destName, group) -> {
            container.setErrorHandler(errorHandler());
        };
    }

    public ErrorHandler errorHandler() {
        return e -> {
            // Handle the exception
            log.error(e.getMessage());
        };
    }
}

//    @Bean
//    public java.util.function.Consumer<ErrorMessage> handleBindingError() {
//        return message -> {
//            log.error("Binding Specific Error Handler: " + message.toString());
//            // Add logic to handle the error (e.g., log it, send notification)
//        };
//    }
//    @Bean
//    public java.util.function.Consumer<ErrorMessage> handleGlobalError() {
//        return message -> {
//            log.error("Global Error Handler: " + message.toString());
//            // Add logic to handle global errors
//        };
//    }
//
//    @Bean
//    public Function<Flux<String>, Flux<String>> process() {
//        return flux -> flux
//                .retryWhen(Retry.backoff(3, Duration.ofMillis(1000)))
//                .map(String::toUpperCase);
//    }
//}
