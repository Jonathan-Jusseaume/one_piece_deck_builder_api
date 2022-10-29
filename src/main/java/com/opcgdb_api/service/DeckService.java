package com.opcgdb_api.service;

import com.opcgdb_api.dto.Card;
import com.opcgdb_api.dto.Deck;
import com.opcgdb_api.entity.CardEntity;
import com.opcgdb_api.entity.ColorEntity;
import com.opcgdb_api.entity.UserEntity;
import com.opcgdb_api.repository.CardDao;
import com.opcgdb_api.repository.DeckDao;
import com.opcgdb_api.repository.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeckService {

    private final DeckDao deckDao;

    private final UserDao userDao;

    private final CardDao cardDao;

    public Deck create(Deck deck, String language) throws InvalidParameterException {
        Optional<UserEntity> userEntity = userDao.findById(deck.getUser().getMail());
        userEntity.ifPresent(entity -> deck.getUser().setCreationDate(entity.getJoinDate()));
        if (!isDeckValid(deck)) {
            throw new InvalidParameterException("Deck is not valid");
        }
        deck.setId(UUID.randomUUID());
        deck.setCreationDate(new Date(Calendar.getInstance().getTime().getTime()));
        return new Deck(deckDao.save(deck.toEntity()), language);
    }

    private boolean isDeckValid(Deck deck) {
        if (deck.getLeader() == null || deck.getLeader().getId() == null) {
            return false;
        }
        Optional<CardEntity> leaderEntityOptional = cardDao.findById(deck.getLeader().getId());
        if (leaderEntityOptional.isEmpty()) {
            return false;
        }
        if (!validCardsNumbers(deck)) {
            return false;
        }
        return validCardsColors(deck, leaderEntityOptional.get());
    }

    private boolean validCardsNumbers(Deck deck) {
        if (deck.getCards() == null || deck.getCards().size() != 50
                || deck.getCards().stream().anyMatch(card -> card == null || card.getId() == null)) {
            return false;
        }
        Set<String> distinctIds = deck.getCards()
                .stream()
                .map(Card::getId)
                .collect(Collectors.toSet());
        return distinctIds.stream().noneMatch(id ->
                deck.getCards()
                        .stream()
                        .filter(card -> card.getId().equals(id)).count() > 4
        );
    }

    private boolean validCardsColors(Deck deck, CardEntity leaderEntity) {
        List<CardEntity> cardEntities = cardDao.findAllById(deck.getCards()
                .stream()
                .map(Card::getId)
                .collect(Collectors.toList()));
        return cardEntities.stream().allMatch(cardEntity ->
                hasCardColorOfLeader(leaderEntity,
                        retrieveCardEntityFromListById(cardEntities, cardEntity.getId())));
    }

    private CardEntity retrieveCardEntityFromListById(List<CardEntity> cardEntities, String id) {
        return cardEntities
                .stream()
                .filter(cardEntity -> cardEntity.getId().equals(id))
                .findFirst().orElse(null);
    }

    private boolean hasCardColorOfLeader(CardEntity leader, CardEntity cardSelected) {
        return leader.getColors()
                .stream()
                .anyMatch(color -> cardSelected != null &&
                        cardSelected.getColors()
                                .stream()
                                .map(ColorEntity::getId)
                                .anyMatch(colorId -> color.getId().equals(colorId)));
    }


}
