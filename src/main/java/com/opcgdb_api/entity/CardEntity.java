package com.opcgdb_api.entity;

import com.opcgdb_api.dto.Deck;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "UT_CARD", schema = "public")
public class CardEntity {

    @Id
    @Column(name = "ID", nullable = false)
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TYPE_ID")
    private TypeEntity type;

    @ManyToMany
    @JoinTable(name = "UT_CARD_PRODUCT",
            joinColumns = @JoinColumn(name = "CARD_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID")
    )
    private Set<ProductEntity> products;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RARITY_ID")
    private RarityEntity rarity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ATTRIBUTE_ID")
    private AttributeEntity attribute;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "CARD_ID")
    private Set<CardDescriptionEntity> descriptions;

    @ManyToMany
    @JoinTable(name = "UT_CARD_COLOR",
            joinColumns = @JoinColumn(name = "CARD_ID"),
            inverseJoinColumns = @JoinColumn(name = "COLOR_ID")
    )
    private Set<ColorEntity> colors;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "UT_CARD_DECK",
            joinColumns = @JoinColumn(name = "CARD_ID"),
            inverseJoinColumns = @JoinColumn(name = "DECK_ID")
    )
    private Set<DeckEntity> decks;

    @ManyToMany
    @JoinTable(name = "UT_CARD_TAG",
            joinColumns = @JoinColumn(name = "CARD_ID"),
            inverseJoinColumns = @JoinColumn(name = "TAG_ID")
    )
    private Set<TagEntity> tags;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "CARD_ID")
    private Set<CardImageEntity> images;

    @Column(name = "COST")
    private Integer cost;

    @Column(name = "POWER")
    private Integer power;

    @Column(name = "LIFE")
    private Integer life;

    @Column(name = "COUNTER")
    private Integer counter;

}
