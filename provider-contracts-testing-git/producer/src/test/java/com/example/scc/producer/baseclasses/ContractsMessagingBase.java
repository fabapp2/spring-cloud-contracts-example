package com.example.scc.producer.baseclasses;

import com.example.scc.producer.MessageOutboundAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext
@AutoConfigureMessageVerifier
public class ContractsMessagingBase {

    @Autowired
    MessageOutboundAdapter messageOutboundAdapter;

    public void sendMessage() {
        messageOutboundAdapter.sendMessage("Hello");
    }

    public void assertDoubleEqualsWithTolerance(String it, int tolerance) {
        assertThat(System.currentTimeMillis() - Double.valueOf(it) < tolerance);
    }
}