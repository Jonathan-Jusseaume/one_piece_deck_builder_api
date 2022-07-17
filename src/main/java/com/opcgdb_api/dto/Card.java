package com.opcgdb_api.dto;

import com.opcgdb_api.constant.LanguageCodeEnum;
import com.opcgdb_api.entity.CardDescriptionEntity;
import com.opcgdb_api.entity.CardEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Accessors(chain = true)
public class Card {

    private String id;

    private Type type;

    private Product product;

    private List<Color> colors;

    private List<Tag> tags;

    private Attribute attribute;

    private String label;

    private String effect;

    private Long cost;

    private String image;

    private Long power;

    private Long life;

    private Long counter;

    private Rarity rarity;

    public Card(CardEntity cardEntity, String languageCode) {
        if (!LanguageCodeEnum.languageIsAvailable(languageCode)) {
            languageCode = LanguageCodeEnum.ENGLISH.toString();
        }
        this.id = cardEntity.getId();
        this.type = new Type(cardEntity.getType(), languageCode);
        this.product = new Product(cardEntity.getProduct(), languageCode);
        this.rarity = new Rarity(cardEntity.getRarity());
        if (cardEntity.getAttribute() != null) {
            this.attribute = new Attribute(cardEntity.getAttribute(), languageCode);
        }
        this.cost = cardEntity.getCost();
        this.counter = cardEntity.getCounter();
        this.life = cardEntity.getLife();
        this.image = cardEntity.getImage();
        this.power = cardEntity.getPower();
        String finalLanguageCode = languageCode;
        this.colors = cardEntity.getColors()
                .stream()
                .map(colorEntity -> new Color(colorEntity, finalLanguageCode))
                .collect(Collectors.toList());
        this.tags = cardEntity.getTags()
                .stream()
                .map(tagEntity -> new Tag(tagEntity, finalLanguageCode))
                .collect(Collectors.toList());
        for (CardDescriptionEntity description : cardEntity.getDescriptions()) {
            if (description.getLanguageCode().equals(languageCode)) {
                this.label = description.getName();
                this.effect = description.getEffect();
            }
        }
    }

}
