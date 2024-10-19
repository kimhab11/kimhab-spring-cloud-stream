package com.example.publisher;

import com.example.publisher.customBinding.KimhabBinding;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@EnableBinding(KimhabBinding.class)
@Service
@Slf4j
public class PublisherService {
    @Qualifier("kimhabChannel")
    @Autowired
    private MessageChannel messageChannel;

    @Autowired
    private KimhabBinding source;

    @SneakyThrows
    public void makeMessage(@RequestParam String message) {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> messageMap = new HashMap<>();
        messageMap.put("data", message);
        messageMap.put("time", LocalTime.now().toString());
        messageMap.put("app", "publisher-app");
        String json = objectMapper.writeValueAsString(messageMap);
        log.info("json: {}", json);


        //  messageChannel.send(MessageBuilder.withPayload(message).build());
        Message<String> msg = MessageBuilder.withPayload(json).build();
        source.kimhabSource().send(msg);
        System.out.println("Sent message: " + msg);
    }
}
