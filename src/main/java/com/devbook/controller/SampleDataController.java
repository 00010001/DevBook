package com.devbook.controller;

import com.devbook.service.SampleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SampleDataController {

    private SampleDataService sampleDataService;

    @Autowired
    public SampleDataController(SampleDataService sampleDataService) {
        this.sampleDataService = sampleDataService;
    }

    @GetMapping(value = "/addSampleData")
    public void addSampleDataToDatabase() {
        sampleDataService.addSampleDataToDatabase(true);
    }

}
