package com.opcgdb_api.service;

import com.opcgdb_api.dto.Card;
import com.opcgdb_api.dto.Deck;
import com.opcgdb_api.dto.User;
import com.opcgdb_api.entity.CardEntity;
import com.opcgdb_api.entity.ColorEntity;
import com.opcgdb_api.entity.DeckEntity;
import com.opcgdb_api.entity.UserEntity;
import com.opcgdb_api.repository.CardDao;
import com.opcgdb_api.repository.DeckDao;
import com.opcgdb_api.repository.UserDao;
import com.opcgdb_api.repository.specification.DeckSpecification;
import com.opcgdb_api.repository.specification.SpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.security.InvalidParameterException;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DeckService {

    private final DeckDao deckDao;

    private final UserDao userDao;

    private final CardDao cardDao;

    public Page<Deck> list(Pageable pageable, String mail, Set<Long> colorsId, String keyword, User connectedUser,
                           boolean onlyFavorite, String language) {
        if (pageable == null) {
            pageable = Pageable.ofSize(25);
        }
        SpecificationBuilder<DeckEntity> builder = new SpecificationBuilder<>();
        builder.with(DeckSpecification.distinct());
        addMailToFilter(builder, mail);
        addColorsToFilter(builder, colorsId);
        addKeywordToFilter(builder, keyword);
        addOnlyFavoriteToFilter(builder, connectedUser, onlyFavorite);
        Page<DeckEntity> results = deckDao.findAll(builder.build(), pageable);
        return new PageImpl<>(
                results.getContent()
                        .stream()
                        .map(cardEntity -> new Deck(cardEntity, language, mail))
                        .collect(Collectors.toList()),
                pageable, results.getTotalElements());
    }

    public Deck read(UUID id, String language, String mail) throws ResponseStatusException {
        return new Deck(this.readById(id), language, mail);
    }

    public Deck create(Deck deck, String language) throws InvalidParameterException {
        if (!isDeckValid(deck)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Deck is not valid");
        }
        UserEntity userToSave = this.saveUserIfNotExists(deck.getUser());
        deck.setId(UUID.randomUUID());
        deck.setUser(new User(userToSave));
        deck.setCreationDate(new Date(Calendar.getInstance().getTime().getTime()));
        return new Deck(deckDao.save(deck.toEntity()), language, userToSave.getMail());
    }

    public Deck favorite(UUID id, User connectedUser, String language) throws ResponseStatusException {
        return favoriteAction(id, connectedUser, language, true);
    }

    public Deck unfavorite(UUID id, User connectedUser, String language) {
        return favoriteAction(id, connectedUser, language, false);
    }

    private Deck favoriteAction(UUID id, User connectedUser, String language, boolean makeFavorite) throws ResponseStatusException {
        DeckEntity deckEntity = this.readById(id);
        UserEntity userToSave = this.saveUserIfNotExists(connectedUser);
        if (!deckEntity.canLikeDeck(userToSave.getMail()) && makeFavorite) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "You have already make this deck as favorite");
        }
        if (deckEntity.canLikeDeck(userToSave.getMail()) && !makeFavorite) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "You can't unfavorite a deck which is not your favorite");
        }

        if (makeFavorite) {
            deckEntity.getUsersFavorite().add(userToSave);
            deckEntity.setCountFavorites(deckEntity.getCountFavorites() + 1);
        } else {
            deckEntity.setUsersFavorite(deckEntity.getUsersFavorite()
                    .stream()
                    .filter(userEntity -> !userEntity.getMail().equals(connectedUser.getMail()))
                    .collect(Collectors.toList()));
            deckEntity.setCountFavorites(deckEntity.getCountFavorites() - 1);
        }
        return new Deck(deckDao.save(deckEntity), language, userToSave.getMail());
    }

    public void delete(UUID id, String mail) throws ResponseStatusException {
        Optional<DeckEntity> deckEntity = deckDao.findById(id);
        if (deckEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Deck with the given id doesn't exist");
        }
        if (!deckEntity.get().getUser().getMail().equals(mail)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This is not your deck");
        }
        this.deckDao.deleteById(id);
    }

    private UserEntity saveUserIfNotExists(User user) {
        Optional<UserEntity> optionalUserEntity = userDao.findById(user.getMail());
        return optionalUserEntity.orElseGet(() -> userDao.saveAndFlush(user.toEntity()));
    }

    private DeckEntity readById(UUID id) throws ResponseStatusException {
        Optional<DeckEntity> deckEntity = deckDao.findById(id);
        if (deckEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Deck with the given id doesn't exist");
        }
        return deckEntity.get();
    }

    private boolean isDeckValid(Deck deck) {
        if (deck.getLeader() == null || deck.getLeader().getId() == null || deck.getName() == null
                || deck.getName().isEmpty()) {
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

    private void addMailToFilter(SpecificationBuilder<DeckEntity> builder, String mail) {
        if (mail != null && !mail.isEmpty()) {
            builder.with(DeckSpecification.byUserMail(mail));
        }
    }

    private void addKeywordToFilter(SpecificationBuilder<DeckEntity> builder, String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            builder.with(DeckSpecification.byKeyword(keyword));
        }
    }

    private void addColorsToFilter(SpecificationBuilder<DeckEntity> builder, Set<Long> colorsId) {
        if (colorsId != null && !colorsId.isEmpty()) {
            builder.with(DeckSpecification.byColorId(colorsId));
        }
    }

    private void addOnlyFavoriteToFilter(SpecificationBuilder<DeckEntity> builder, User connectedUser, boolean onlyFavorite) {
        if (connectedUser != null && connectedUser.getMail() != null && onlyFavorite) {
            builder.with(DeckSpecification.byUserFavoriteDeck(connectedUser.getMail()));
        }
    }


}
