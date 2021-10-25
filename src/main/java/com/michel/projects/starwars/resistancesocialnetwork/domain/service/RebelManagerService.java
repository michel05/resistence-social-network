package com.michel.projects.starwars.resistancesocialnetwork.domain.service;

import com.michel.projects.starwars.resistancesocialnetwork.domain.exceptions.RebelNotFoundException;
import com.michel.projects.starwars.resistancesocialnetwork.domain.gateway.RebelGateway;
import com.michel.projects.starwars.resistancesocialnetwork.domain.model.Rebel;
import com.michel.projects.starwars.resistancesocialnetwork.domain.model.RebelLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RebelManagerService {

    @Autowired
    private RebelGateway rebelGateway;
    @Autowired
    private BetrayerValidation betrayerValidation;

    public Long createRebel(Rebel rebel) {
        return rebelGateway.saveRebel(rebel);
    }

    public void updateRebelLocation(Long rebelId, RebelLocation location) throws RebelNotFoundException {
        rebelGateway.findRebel(rebelId).orElseThrow(RebelNotFoundException::new);
        rebelGateway.updateLocation(rebelId, location);
    }

    public Integer reportBetrayer(Long betrayerRebelId) {
        var totalMarks = rebelGateway.addAndRetrieveBetrayerMarks(betrayerRebelId);
        if (betrayerValidation.validateIfBetrayer(totalMarks)) {
            rebelGateway.setRebelAsBetrayer(betrayerRebelId);
        }
        return totalMarks;
    }
}
