package com.michel.projects.starwars.resistancesocialnetwork.domain.service;

import com.michel.projects.starwars.resistancesocialnetwork.domain.gateway.RebelGateway;
import com.michel.projects.starwars.resistancesocialnetwork.domain.model.InventoryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RebelReportService {

    @Autowired
    private RebelGateway rebelGateway;

    public Double calculateBetrayerPercentage() {
        var totalRebels = rebelGateway.getNumberOfRebels();
        if(totalRebels == 0L) return (double) 0;
        var totalBetrayers = rebelGateway.getNumberOfRebels(true);
        return ((Double.valueOf(totalBetrayers) / Double.valueOf(totalRebels))) * 100;
    }

    public Double calculateRebelPercentage() {
        var totalRebels = rebelGateway.getNumberOfRebels();
        if(totalRebels == 0L) return (double) 0;
        var totalNotBetrayers = rebelGateway.getNumberOfRebels(false);
        return  ((Double.valueOf(totalNotBetrayers) / Double.valueOf(totalRebels))) * 100;
    }

    public List<InventoryItem> calculateResourceAverage() {
        return rebelGateway.getResourceAverageByRebel();
    }

    public Long calculateLostPointsByBetrayers() {
        return rebelGateway.getLostPointsByBetrayers();
    }
}
