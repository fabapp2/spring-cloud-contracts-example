package com.example.scc.producer;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@AutoConfigureMockMvc
@AutoConfigureMessageVerifier
public class HelloServiceSccBase {

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Autowired
    MessageOutboundAdapter messageOutboundAdapter;

    public void sendMessage() {
        messageOutboundAdapter.sendMessage("Hello");
    }

    public void assertDoubleEqualsWithTolerance(String it, int tolerance) {
        assertThat(System.currentTimeMillis() - Double.valueOf(it) < tolerance);
    }
}