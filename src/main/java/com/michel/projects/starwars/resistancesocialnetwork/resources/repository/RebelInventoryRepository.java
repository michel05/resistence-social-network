package com.michel.projects.starwars.resistancesocialnetwork.resources.repository;

import com.michel.projects.starwars.resistancesocialnetwork.resources.repository.entity.RebelInventoryItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RebelInventoryRepository extends JpaRepository<RebelInventoryItemEntity, Long> {

    @Query(value = "SELECT AVG(QUANTITY) as quantity, ITEM_TYPE as itemType " +
            "FROM REBEL_INVENTORY_ITEM GROUP BY ITEM_TYPE", nativeQuery = true)
    List<ResourceAverage> resourceAverage();

    @Query(value = "SELECT SUM(r.quantity) FROM rebel_inventory_item r where r.rebel.betrayer = true")
    Optional<Long> sumResourcePointsByBetrayer();
}