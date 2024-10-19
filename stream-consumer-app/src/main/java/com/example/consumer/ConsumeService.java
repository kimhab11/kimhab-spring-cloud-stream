package com.example.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class ConsumeService {

    @StreamListener(Sink.INPUT)
    public void receivedMessage(Message<String> message){
        // Extract Message
        MessageHeaders headers = message.getHeaders();
        Object contentType = message.getHeaders().get("contentType");

        // Process the message
        log.info("Data Reviewed: {}, messageId: {}, contentType: {}", message.getPayload(), headers.getId(), contentType);

    }
}
