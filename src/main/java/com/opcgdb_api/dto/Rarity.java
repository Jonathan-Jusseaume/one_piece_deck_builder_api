package com.opcgdb_api.dto;

import com.opcgdb_api.entity.RarityEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Rarity {

    private Long id;

    private String label;

    public Rarity(RarityEntity rarityEntity) {
        this.id = rarityEntity.getId();
        this.label = rarityEntity.getLabel();
    }
}
