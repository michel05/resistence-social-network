package com.michel.projects.starwars.resistancesocialnetwork.application.web.controller;

import com.michel.projects.starwars.resistancesocialnetwork.application.web.controller.response.PercentageResponse;
import com.michel.projects.starwars.resistancesocialnetwork.application.web.controller.response.ResourcePointsResponse;
import com.michel.projects.starwars.resistancesocialnetwork.domain.service.RebelReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/social-network/reports")
public class ResistanceSocialNetworkReportsController {

    @Autowired
    private RebelReportService rebelReportService;

    @GetMapping(value = "/betrayers/percentage", produces = "application/json")
    public ResponseEntity<PercentageResponse> getPercentageOfBetrayers() {
        var percentage = rebelReportService.calculateBetrayerPercentage();
        return new ResponseEntity<>(new PercentageResponse(percentage), HttpStatus.OK);
    }

    @GetMapping(value = "/rebels/percentage", produces = "application/json")
    public ResponseEntity<PercentageResponse> getPercentageOfRebels() {
        var percentage = rebelReportService.calculateRebelPercentage();
        return new ResponseEntity<>(new PercentageResponse(percentage), HttpStatus.OK);
    }

    @GetMapping(value = "/resources", produces = "application/json")
    public ResponseEntity getResourceAverages() {
        var items = rebelReportService.calculateResourceAverage();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping(value = "/resources/points/lost-by-betrayer", produces = "application/json")
    public ResponseEntity getLostPointsByBetrayers() {
        var points = rebelReportService.calculateLostPointsByBetrayers();
        return new ResponseEntity<>(new ResourcePointsResponse(points), HttpStatus.OK);
    }
}
