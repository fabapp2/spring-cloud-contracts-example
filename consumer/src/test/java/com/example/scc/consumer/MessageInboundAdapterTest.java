package com.example.scc.consumer;

        import org.junit.jupiter.api.Test;
        import org.mockito.Mockito;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.boot.test.mock.mockito.MockBean;
        import org.springframework.cloud.stream.messaging.Sink;
        import org.springframework.cloud.stream.messaging.Source;
        import org.springframework.messaging.Message;
        import org.springframework.messaging.support.MessageBuilder;

        import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MessageInboundAdapterTest {

    @Autowired
    Sink sink;

    @MockBean
    ConsumerOfHelloService consumerOfHelloService;

    @Test
    public void inboundMessageShouldBeProcessedByTemperatureMonitoring() {
        String payload = "Hello";
        Message<String> message = MessageBuilder.withPayload(payload).build();
        sink.input().send(message);
        Mockito.verify(consumerOfHelloService).handleHelloMessage(payload);
    }

}
