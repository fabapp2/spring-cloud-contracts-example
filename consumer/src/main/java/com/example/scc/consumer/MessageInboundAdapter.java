package com.example.scc.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

@Component
@EnableBinding(Sink.class)
@RequiredArgsConstructor
public class MessageInboundAdapter {

    private final ConsumerOfHelloService consumerOfHelloService;

    @StreamListener(Sink.INPUT)
    public void onMessage(Message<String> message) {
        consumerOfHelloService.handleHelloMessage(message.getPayload());
    }

}
