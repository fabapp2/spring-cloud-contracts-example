package com.example.scc.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

@EnableBinding(Sink.class)
@RequiredArgsConstructor
public class MessageInboundAdapter {

    private final ConsumerOfHelloService consumerOfHelloService;

    @StreamListener(Sink.INPUT)
    public void onMessage(Message<String> message) {
        consumerOfHelloService.handleHelloMessage(message.getPayload());
    }
}
