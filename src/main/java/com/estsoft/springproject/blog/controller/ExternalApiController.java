package com.estsoft.springproject.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ExternalApiController {
    private static final Logger log = LoggerFactory.getLogger(ExternalApiController.class);

    @GetMapping("/api/external")
    public String callApi() {
        //외부 API 호출
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> json = restTemplate.getForEntity
                ("http://jsonplaceholder.typicode.com/posts", String.class);

        log.info("StatusCode: {}", json.getStatusCode());
        log.info(json.getBody());

        //역직렬화 (json -> object)

        return "OK";
    }
}
