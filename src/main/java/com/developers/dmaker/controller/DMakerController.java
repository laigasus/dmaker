package com.developers.dmaker.controller;

import com.developers.dmaker.service.DMakerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class DMakerController {
    private static final Logger log = LoggerFactory.getLogger(DMakerController.class);
    private final DMakerService dMakerService;

    public DMakerController(DMakerService dMakerService) {
        this.dMakerService = dMakerService;
    }

    @GetMapping({"/developers"})
    public List<String> getAllDevelopers() {
        log.info("GET /developers HTTP/1.1");
        return Arrays.asList("snow", "elsa", "olaf");
    }

    @GetMapping({"/create-developer"})
    public List<String> createDevelopers() {
        log.info("GET /create-developer HTTP/1.1");
        dMakerService.createDeveloper();
        return Collections.singletonList("olaf");
    }
}