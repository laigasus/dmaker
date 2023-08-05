package com.developers.dmaker.controller;

import com.developers.dmaker.dto.CreateDeveloper;
import com.developers.dmaker.dto.DeveloperDetailDto;
import com.developers.dmaker.dto.DeveloperDto;
import com.developers.dmaker.dto.EditDeveloper;
import com.developers.dmaker.service.DMakerService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DMakerController {
    private static final Logger log = LoggerFactory.getLogger(DMakerController.class);

    private final DMakerService dMakerService;

    public DMakerController(DMakerService dMakerService) {
        this.dMakerService = dMakerService;
    }

    @GetMapping({"/developers"})
    public List<DeveloperDto> getAllDevelopers() {
        log.info("GET /developers HTTP/1.1");
        return dMakerService.getAllDevelopers();
    }

    @GetMapping({"/developers/{memberId}"})
    public DeveloperDetailDto getDeveloperDetail(
            @PathVariable final String memberId
    ) {
        log.info("GET /developers HTTP/1.1");
        return dMakerService.getDeveloperDetail(memberId);
    }


    @PostMapping({"/create-developer"})
    public CreateDeveloper.Response createDeveloper(@Valid @RequestBody final CreateDeveloper.Request request) {
        log.info("GET /create-developer HTTP/1.1");
        log.info("request : {}", request);

        return dMakerService.createDeveloper(request);
    }

    @PatchMapping({"/developer/{memberId}"})
    public DeveloperDetailDto editDeveloper(
            @PathVariable final String memberId,
            @Valid @RequestBody final EditDeveloper.Request request
    ) {
        return dMakerService.editDeveloper(memberId, request);
    }

    @DeleteMapping({"/developer/delete/{memberId}"})
    public DeveloperDetailDto deleteDeveloper(
            @PathVariable final String memberId
    ) {
        return dMakerService.deleteDeveloper(memberId);
    }
}