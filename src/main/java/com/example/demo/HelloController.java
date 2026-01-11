package com.example.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private static final Logger logger = LogManager.getLogger(HelloController.class);

    @GetMapping("/")
    public String hello() {
        logger.info("Received request to / endpoint");
        logger.debug("Processing hello request");
        String response = "Hello, Spring!";
        logger.info("Returning response: {}", response);
        return response;
    }
}
