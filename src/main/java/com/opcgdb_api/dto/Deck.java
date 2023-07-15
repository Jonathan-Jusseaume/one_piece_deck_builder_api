package com.opcgdb_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opcgdb_api.entity.CardEntity;
import com.opcgdb_api.entity.DeckEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class Deck {

    private UUID id;

    private List<Card> cards;

    private Card leader;

    private String name;

    private String description;

    @JsonIgnore
    private User user;

    private Date creationDate;

    private Integer countFavorites = 0;

    private boolean isFavorite = false;

    public Deck(DeckEntity deckEntity, String languageCode, String mail, boolean complete) {
        this.id = deckEntity.getId();
        if (complete) {
            this.cards = deckEntity.getCards().stream()
                    .map(cardEntity -> new Card(cardEntity, languageCode))
                    .collect(Collectors.toList());
            this.description = deckEntity.getDescription();
        }
        this.leader = new Card(deckEntity.getLeader(), languageCode);
        this.name = deckEntity.getName();
        this.creationDate = deckEntity.getCreationDate();
        this.countFavorites = deckEntity.getCountFavorites();
        this.isFavorite = deckEntity.isFavorite(mail);
    }

    public DeckEntity toEntity() {
        return new DeckEntity()
                .setId(id)
                .setUser(user.toEntity())
                .setCards(cards.stream()
                        .map(card -> new CardEntity()
                                .setId(card.getId()))
                        .collect(Collectors.toList()))
                .setLeader(new CardEntity().setId(leader.getId()))
                .setDescription(description)
                .setName(name)
                .setCreationDate(creationDate)
                .setUsersFavorite(Collections.emptySet())
                .setCountFavorites(countFavorites);
    }
}
