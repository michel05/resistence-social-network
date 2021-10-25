package com.michel.projects.starwars.resistancesocialnetwork.domain.service;

import com.michel.projects.starwars.resistancesocialnetwork.domain.exceptions.BetrayerRebelCanNotExchangeException;
import com.michel.projects.starwars.resistancesocialnetwork.domain.exceptions.ItemsHaveNoSamePointQuantityException;
import com.michel.projects.starwars.resistancesocialnetwork.domain.exceptions.RebelHasNoItemToExchangeException;
import com.michel.projects.starwars.resistancesocialnetwork.domain.exceptions.RebelNotFoundException;
import com.michel.projects.starwars.resistancesocialnetwork.domain.gateway.RebelGateway;
import com.michel.projects.starwars.resistancesocialnetwork.domain.model.Inventory;
import com.michel.projects.starwars.resistancesocialnetwork.domain.model.InventoryItem;
import com.michel.projects.starwars.resistancesocialnetwork.domain.model.Rebel;
import com.michel.projects.starwars.resistancesocialnetwork.domain.model.SupplyExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExchangeMarketManagerService {

    @Autowired
    private RebelGateway rebelGateway;

    public void exchangeItems(SupplyExchange supplyExchange) throws ItemsHaveNoSamePointQuantityException {
        validatePointQuantity(supplyExchange);

        var requesterRebel = rebelGateway.findRebel(supplyExchange.getRequesterOffer().getRebelId())
                .orElseThrow(RebelNotFoundException::new);

        var receiverRebel = rebelGateway.findRebel(supplyExchange.getReceiverOffer().getRebelId())
                .orElseThrow(RebelNotFoundException::new);

        validateRebelEligibilityToExchange(requesterRebel);
        validateRebelEligibilityToExchange(receiverRebel);
        validateIfRebelHasInventory(requesterRebel, supplyExchange.getRequesterOffer().getItems());
        validateIfRebelHasInventory(receiverRebel, supplyExchange.getReceiverOffer().getItems());

        requesterRebel.getInventory().exchangeItems(
                supplyExchange.getReceiverOffer().getItems(),
                supplyExchange.getRequesterOffer().getItems()
        );
        receiverRebel.getInventory().exchangeItems(
                supplyExchange.getRequesterOffer().getItems(),
                supplyExchange.getReceiverOffer().getItems()
                );

        Map<Long, Inventory> rebelInventoryMap = new HashMap<>();
        rebelInventoryMap.put(supplyExchange.getRequesterOffer().getRebelId(), requesterRebel.getInventory());
        rebelInventoryMap.put(supplyExchange.getReceiverOffer().getRebelId(), receiverRebel.getInventory());

        rebelGateway.updateInventory(rebelInventoryMap);
    }

    private void validateRebelEligibilityToExchange(Rebel rebel) {
        if (rebel.isBetrayer()) {
            throw new BetrayerRebelCanNotExchangeException();
        }
    }

    private void validateIfRebelHasInventory(Rebel rebel, List<InventoryItem> items) {
        items.forEach(it -> {
            var item = rebel.getInventory().getItemByType(it.getItemType())
                    .orElseThrow(RebelHasNoItemToExchangeException::new);
            if (it.getQuantity() > item.getQuantity()) {
                throw new RebelHasNoItemToExchangeException();
            }
        });
    }

    private void validatePointQuantity(SupplyExchange supplyExchange) throws ItemsHaveNoSamePointQuantityException {
        var requesterPoints = supplyExchange.getRequesterOffer().getItems()
                .stream()
                .map(item -> item.getTotalValue())
                .reduce(0L, Long::sum);

        var customersPoints = supplyExchange.getReceiverOffer().getItems()
                .stream()
                .map(item -> item.getTotalValue())
                .reduce(0L, Long::sum);

        if (!requesterPoints.equals(customersPoints)) {
            throw new ItemsHaveNoSamePointQuantityException();
        }
    }

}
