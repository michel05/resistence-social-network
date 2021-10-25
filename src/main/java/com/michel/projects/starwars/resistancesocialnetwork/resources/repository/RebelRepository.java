package com.michel.projects.starwars.resistancesocialnetwork.resources.repository;

import com.michel.projects.starwars.resistancesocialnetwork.resources.repository.entity.RebelEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RebelRepository extends CrudRepository<RebelEntity, Long> {

    Long countByBetrayer(boolean betrayer);
}