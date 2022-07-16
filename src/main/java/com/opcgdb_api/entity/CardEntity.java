package com.opcgdb_api.entity;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private ProductEntity product;

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

    @ManyToMany
    @JoinTable(name = "UT_CARD_TAG",
            joinColumns = @JoinColumn(name = "CARD_ID"),
            inverseJoinColumns = @JoinColumn(name = "TAG_ID")
    )
    private Set<TagEntity> tags;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "COST")
    private Long cost;

    @Column(name = "POWER")
    private Long power;

    @Column(name = "LIFE")
    private Long life;

    @Column(name = "COUNTER")
    private Long counter;

}
