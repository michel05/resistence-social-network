package com.michel.projects.starwars.resistancesocialnetwork.domain.gateway;

import com.michel.projects.starwars.resistancesocialnetwork.domain.model.Inventory;
import com.michel.projects.starwars.resistancesocialnetwork.domain.model.InventoryItem;
import com.michel.projects.starwars.resistancesocialnetwork.domain.model.Rebel;
import com.michel.projects.starwars.resistancesocialnetwork.domain.model.RebelLocation;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RebelGateway {

    Long saveRebel(Rebel rebel);

    Optional<Rebel> findRebel(Long rebelId);

    void updateLocation(Long rebelId, RebelLocation location);

    Integer addAndRetrieveBetrayerMarks(Long rebelId);

    void setRebelAsBetrayer(Long rebelId);

    void updateInventory(Map<Long, Inventory> rebelInventoryMap);

    Long getNumberOfRebels(boolean betrayer);

    Long getNumberOfRebels();

    List<InventoryItem> getResourceAverageByRebel();

    Long getLostPointsByBetrayers();
}
