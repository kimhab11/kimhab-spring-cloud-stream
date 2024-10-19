package com.example.publisher;

import com.example.publisher.customBinding.KimhabBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@EnableBinding(KimhabBinding.class)
@Service
public class PublisherService {
    @Qualifier("kimhabChannel")
    @Autowired
    private MessageChannel messageChannel;

    @Autowired
    private KimhabBinding source;

    public void makeMessage(@RequestParam String message){
      //  messageChannel.send(MessageBuilder.withPayload(message).build());
        Message<String> msg = MessageBuilder.withPayload(message).build();
       source.kimhabSource().send(msg);
        System.out.println("Sent message: " + msg);
    }
}
