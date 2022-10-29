package com.opcgdb_api.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "UT_DECK", schema = "public")
public class DeckEntity {

    @Id
    @Column(name = "ID", nullable = false)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LEADER_ID")
    private CardEntity leader;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_MAIL")
    private UserEntity user;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "UT_DECK_CARD",
            joinColumns = @JoinColumn(name = "DECK_ID"),
            inverseJoinColumns = @JoinColumn(name = "CARD_ID")
    )
    private List<CardEntity> cards;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @Column(name = "DESCRIPTION")
    private String description;

}
