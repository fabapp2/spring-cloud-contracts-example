package com.example.scc.producer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import java.util.concurrent.BlockingQueue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.cloud.stream.test.matcher.MessageQueueMatcher.receivesPayloadThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MessageOutboundAdapterTest {

    @Autowired
    MessageCollector messageCollector;

    @Autowired
    @Qualifier("output")
    MessageChannel output;

    @Autowired
    MessageOutboundAdapter messageOutboundAdapter;

    @Test
    public void retrieve_message() {
        BlockingQueue<Message<?>> messages = messageCollector.forChannel(output);
        messageOutboundAdapter.sendMessage("Hello");
        assertThat(messages, receivesPayloadThat(is("Hello")));
    }

}
