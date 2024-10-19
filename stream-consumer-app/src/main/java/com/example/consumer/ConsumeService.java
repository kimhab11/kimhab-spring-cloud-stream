package com.example.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class ConsumeService {
    @StreamListener(Sink.INPUT)
    private void receivedMessage(String msg){
        log.info("Received Message: {}, time: {}", msg, new Date());
    }
}
