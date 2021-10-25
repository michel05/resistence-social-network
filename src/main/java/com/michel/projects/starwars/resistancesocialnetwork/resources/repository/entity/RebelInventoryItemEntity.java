package com.michel.projects.starwars.resistancesocialnetwork.resources.repository.entity;

import com.michel.projects.starwars.resistancesocialnetwork.domain.model.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "rebel_inventory_item")
public class RebelInventoryItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "item_type")
    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    @ManyToOne
    @JoinColumn(name = "rebel_id")
    private RebelEntity rebel;

    public RebelInventoryItemEntity(Long quantity, ItemType itemType) {
        this.quantity = quantity;
        this.itemType = itemType;
    }
}
