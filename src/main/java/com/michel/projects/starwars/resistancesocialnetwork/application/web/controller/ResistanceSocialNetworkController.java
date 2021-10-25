package com.michel.projects.starwars.resistancesocialnetwork.application.web.controller;

import com.michel.projects.starwars.resistancesocialnetwork.application.web.controller.response.RebelBetrayerMarkResponse;
import com.michel.projects.starwars.resistancesocialnetwork.application.web.controller.response.RebelCreatedResponse;
import com.michel.projects.starwars.resistancesocialnetwork.domain.model.Rebel;
import com.michel.projects.starwars.resistancesocialnetwork.domain.model.RebelLocation;
import com.michel.projects.starwars.resistancesocialnetwork.domain.model.SupplyExchange;
import com.michel.projects.starwars.resistancesocialnetwork.domain.service.ExchangeMarketManagerService;
import com.michel.projects.starwars.resistancesocialnetwork.domain.service.RebelManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/social-network")
public class ResistanceSocialNetworkController {

    @Autowired
    private RebelManagerService rebelManagerService;

    @Autowired
    private ExchangeMarketManagerService exchangeMarketManagerService;

    @PostMapping(value = "/rebels", produces = "application/json")
    public ResponseEntity registerRebel(@RequestBody Rebel rebel) {
        var id = rebelManagerService.createRebel(rebel);
        return new ResponseEntity<>(new RebelCreatedResponse(id), HttpStatus.CREATED);
    }

    @PutMapping(value = "/rebels/{id}/location", produces = "application/json")
    public ResponseEntity updateRebelLocation(
            @PathVariable(value = "id") Long rebelId,
            @RequestBody RebelLocation location) {
        rebelManagerService.updateRebelLocation(rebelId, location);
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @PostMapping(value = "/rebels/betrayer/{id}", produces = "application/json")
    public ResponseEntity reportBetrayerRebel(@PathVariable(value = "id") Long rebelId) {
        var marks = rebelManagerService.reportBetrayer(rebelId);
        return new ResponseEntity<>(new RebelBetrayerMarkResponse(marks), HttpStatus.OK);
    }

    @PostMapping(value = "/market/exchange")
    public ResponseEntity exchangeItems(@RequestBody SupplyExchange supplyExchange) {
        exchangeMarketManagerService.exchangeItems(supplyExchange);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
