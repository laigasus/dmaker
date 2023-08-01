package com.developers.dmaker.controller;

import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DMakerController {
    private static final Logger log = LoggerFactory.getLogger(DMakerController.class);

    @GetMapping({"/developers"})
    public List<String> getAllDevelopers() {
        log.info("GET /developers HTTP/1.1");
        return Arrays.asList("snow", "elsa", "olaf");
    }
}
