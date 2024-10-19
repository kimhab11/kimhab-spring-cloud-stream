package com.example.publisher.customBinding;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface KimhabBinding {
    @Output("kimhabChannel")
    MessageChannel kimhabSource();
}
