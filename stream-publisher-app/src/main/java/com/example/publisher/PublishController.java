package com.example.publisher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PublishController {
    @Resource
    PublisherService publisherService;

    @PostMapping("produce")
    public Object makeMessage(@RequestParam String message){
        publisherService.makeMessage(message);
        return message;
    }
}
