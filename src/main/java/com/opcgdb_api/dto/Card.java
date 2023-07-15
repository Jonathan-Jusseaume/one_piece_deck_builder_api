package com.opcgdb_api.dto;

import com.opcgdb_api.entity.CardDescriptionEntity;
import com.opcgdb_api.entity.CardEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class Card {

    private String id;

    private Type type;

    private List<Color> colors;

    private List<Tag> tags;

    private Attribute attribute;

    private String label;

    private String effect;

    private Integer cost;

    private Integer power;

    private Integer life;

    private Integer counter;

    private List<CardImage> images;

    public Card(CardEntity cardEntity, String languageCode) {
        this.id = cardEntity.getId();
        this.type = new Type(cardEntity.getType(), languageCode);
        if (cardEntity.getAttribute() != null) {
            this.attribute = new Attribute(cardEntity.getAttribute(), languageCode);
        }
        this.cost = cardEntity.getCost();
        this.counter = cardEntity.getCounter();
        this.life = cardEntity.getLife();
        this.images = cardEntity.getImages().stream().map(cardImageEntity -> new CardImage(cardImageEntity, languageCode))
                .sorted()
                .collect(Collectors.toList());
        this.power = cardEntity.getPower();
        this.colors = cardEntity.getColors()
                .stream()
                .map(colorEntity -> new Color(colorEntity, languageCode))
                .collect(Collectors.toList());
        this.tags = cardEntity.getTags()
                .stream()
                .map(tagEntity -> new Tag(tagEntity, languageCode))
                .sorted()
                .collect(Collectors.toList());
        for (CardDescriptionEntity description : cardEntity.getDescriptions()) {
            if (description.getLanguageCode().equals(languageCode)) {
                this.label = description.getName();
                this.effect = description.getEffect();
            }
        }
    }

}
