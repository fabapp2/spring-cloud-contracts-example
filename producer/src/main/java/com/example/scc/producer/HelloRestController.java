package com.example.scc.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HelloRestController {

    private final MessageOutboundAdapter messageOutboundAdapter;

    @PostMapping(value = "/messages", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String messages(@RequestBody String message) {
        log.info("Received message" + message);
        messageOutboundAdapter.sendMessage("Inbound Message: " + message);
        return "Hello Consumer!";
    }

}
