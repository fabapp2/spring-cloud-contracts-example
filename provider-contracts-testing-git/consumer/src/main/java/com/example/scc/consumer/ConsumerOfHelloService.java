package com.example.scc.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class ConsumerOfHelloService {

    private final RestTemplateBuilder restTemplateBuilder;

    @Value("${producer.url}")
    private String producerUrl;

    public String useHelloService(String message) {
        final RestTemplate restTemplate = restTemplateBuilder.build();
        URI url = new UriTemplate(producerUrl + "/{path}").expand("messages");
        String body = "Hello, hello";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        return restTemplate.postForObject(url, request, String.class);
    }

}
