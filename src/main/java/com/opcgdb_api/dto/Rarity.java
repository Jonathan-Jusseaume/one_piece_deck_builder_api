package com.opcgdb_api.dto;

import com.opcgdb_api.entity.RarityEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode
public class Rarity implements Comparable<Rarity> {

    private Long id;

    private String label;

    public Rarity(RarityEntity rarityEntity) {
        this.id = rarityEntity.getId();
        this.label = rarityEntity.getLabel();
    }

    @Override
    public int compareTo(Rarity rarity) {
        return getRarityValueById(this.id) - getRarityValueById(rarity.getId());
    }

    private int getRarityValueById(Long id) {
        int[] rarityValues = {0, 1, 2, 4, 5, 3, 6, 7};
        return rarityValues[Math.toIntExact(id)];
    }
}
