package com.opcgdb_api.dto;

import com.opcgdb_api.entity.CardEntity;
import com.opcgdb_api.entity.ColorDescriptionEntity;
import com.opcgdb_api.entity.DeckEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.sql.Date;
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

    private User user;

    public Date creationDate;

    public Deck(DeckEntity deckEntity, String languageCode) {
        this.id = deckEntity.getId();
        this.cards = deckEntity.getCards().stream()
                .map(cardEntity -> new Card(cardEntity, languageCode))
                .collect(Collectors.toList());
        this.leader = new Card(deckEntity.getLeader(), languageCode);
        this.user = new User(deckEntity.getUser());
        this.name = deckEntity.getName();
        this.description = deckEntity.getDescription();
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
                .setCreationDate(creationDate);
    }
}
