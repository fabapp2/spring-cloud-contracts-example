package com.example.scc.producer;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@EnableBinding(Source.class)
@Component
public class MessageOutboundAdapter {

    @Qualifier("output")
    MessageChannel output;

    public void sendMessage(String message) {
         output.send(MessageBuilder.withPayload(message).setHeader("some", System.currentTimeMillis()).build());
    }
}
