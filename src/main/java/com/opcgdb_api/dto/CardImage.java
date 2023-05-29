package com.opcgdb_api.dto;

import com.opcgdb_api.entity.CardImageEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class CardImage implements Comparable<CardImage> {

    private String filename;

    private Rarity rarity;

    private Product product;

    public CardImage(CardImageEntity cardEntity, String languageCode) {
        this.filename = cardEntity.getName();
        this.rarity = new Rarity(cardEntity.getRarity());
        this.product = new Product(cardEntity.getProduct(), languageCode);
    }

    @Override
    public int compareTo(CardImage o) {
        return this.getFilename().compareTo(o.getFilename());
    }
}
