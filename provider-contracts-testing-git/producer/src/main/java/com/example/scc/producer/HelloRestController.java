package com.example.scc.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloRestController {

    @PostMapping(value = "/messages", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String messages(String message) {
        log.info("Received message" + message);
        return "Hello Consumer!";
    }

}
