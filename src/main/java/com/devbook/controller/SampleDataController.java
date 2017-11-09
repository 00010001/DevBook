package com.devbook.controller;

import com.devbook.service.SampleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;


@RestController
public class SampleDataController {

    private SampleDataService sampleDataService;

    @Autowired
    public SampleDataController(SampleDataService sampleDataService) {
        this.sampleDataService = sampleDataService;
    }

    @GetMapping(value = "/addSampleData")
    public RedirectView addSampleDataToDatabase() {
        sampleDataService.addSampleDataToDatabase(true);
        return new RedirectView("/user");
    }

}
