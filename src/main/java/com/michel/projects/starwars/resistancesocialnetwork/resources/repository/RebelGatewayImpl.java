package com.michel.projects.starwars.resistancesocialnetwork.resources.repository;

import com.michel.projects.starwars.resistancesocialnetwork.domain.exceptions.RebelNotFoundException;
import com.michel.projects.starwars.resistancesocialnetwork.domain.gateway.RebelGateway;
import com.michel.projects.starwars.resistancesocialnetwork.domain.model.Inventory;
import com.michel.projects.starwars.resistancesocialnetwork.domain.model.InventoryItem;
import com.michel.projects.starwars.resistancesocialnetwork.domain.model.Rebel;
import com.michel.projects.starwars.resistancesocialnetwork.domain.model.RebelLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RebelGatewayImpl implements RebelGateway {

    @Autowired
    private RebelRepository rebelRepository;
    @Autowired
    private RebelInventoryRepository rebelInventoryRepository;
    @Autowired
    private RebelEntityMapper rebelEntityMapper;

    public Long saveRebel(Rebel rebel) {
        var entitySaved = rebelRepository.save(rebelEntityMapper.mapToEntity(rebel));
        return entitySaved.getId();
    }

    public Optional<Rebel> findRebel(Long rebelId) {
        var entity = rebelRepository.findById(rebelId);
        return entity.map(rebelEntity -> rebelEntityMapper.mapToModel(rebelEntity));
    }

    public void updateLocation(Long rebelId, RebelLocation location) {
        var entity = rebelRepository.findById(rebelId).orElseThrow(RebelNotFoundException::new);
        entity.getRebelLocation().setLatitude(location.getLatitude());
        entity.getRebelLocation().setLongitude(location.getLongitude());
        entity.getRebelLocation().setWarBaseName(location.getWarBaseName());
        rebelRepository.save(entity);
    }

    public Integer addAndRetrieveBetrayerMarks(Long rebelId) {
        var entity = rebelRepository.findById(rebelId).orElseThrow(RebelNotFoundException::new);
        var marks = entity.getBetrayerMark() + 1;
        entity.setBetrayerMark(marks);
        rebelRepository.save(entity);
        return marks;
    }

    public void setRebelAsBetrayer(Long rebelId) {
        var entity = rebelRepository.findById(rebelId).orElseThrow(RebelNotFoundException::new);
        entity.setBetrayer(true);
        rebelRepository.save(entity);
    }

    @Override
    @Transactional
    public void updateInventory(Map<Long, Inventory> rebelInventoryMap) {
        rebelInventoryMap.forEach((rebelId, inventory) -> {
            var entity = rebelRepository.findById(rebelId).orElseThrow(RebelNotFoundException::new);
            rebelRepository.save(rebelEntityMapper.mapInventory(entity, inventory));
        });
    }

    public Long getNumberOfRebels(boolean betrayer) {
        return rebelRepository.countByBetrayer(betrayer);
    }

    public Long getNumberOfRebels() {
        return rebelRepository.count();
    }

    @Override
    public List<InventoryItem> getResourceAverageByRebel() {
        var items = rebelInventoryRepository.resourceAverage();
        return items.stream().map(item -> new InventoryItem(item.getQuantity(), item.getItemType()))
                .collect(Collectors.toList());
    }

    @Override
    public Long getLostPointsByBetrayers() {
        return rebelInventoryRepository.sumResourcePointsByBetrayer().orElse(0L);
    }
}
