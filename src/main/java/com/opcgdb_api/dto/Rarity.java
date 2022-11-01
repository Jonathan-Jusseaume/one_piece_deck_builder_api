package com.opcgdb_api.dto;

import com.opcgdb_api.entity.RarityEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class Rarity implements Comparable {

    private Long id;

    private String label;

    public Rarity(RarityEntity rarityEntity) {
        this.id = rarityEntity.getId();
        this.label = rarityEntity.getLabel();
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Rarity)) {
            return -1;
        }
        return getRarityValueById(this.id) - getRarityValueById(((Rarity) o).getId());
    }

    private int getRarityValueById(Long id) {
        int[] rarityValues = {0, 1, 2, 4, 5, 3};
        return rarityValues[Math.toIntExact(id)];
    }
}
