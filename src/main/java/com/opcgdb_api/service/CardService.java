package com.opcgdb_api.service;

import com.opcgdb_api.dto.*;
import com.opcgdb_api.entity.CardEntity;
import com.opcgdb_api.model.CardFilter;
import com.opcgdb_api.repository.CardDao;
import com.opcgdb_api.repository.specification.CardSpecification;
import com.opcgdb_api.repository.specification.SpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardDao cardDao;

    public List<Card> list(String languageCode) {
        return cardDao.findAll()
                .stream()
                .map(cardEntity -> new Card(cardEntity, languageCode))
                .collect(Collectors.toList());
    }

    public Page<Card> search(CardFilter cardFilter, String languageCode, Pageable pageable) {
        if (pageable == null) {
            pageable = Pageable.ofSize(25);
        }

        SpecificationBuilder<CardEntity> builder = new SpecificationBuilder<>();
        builder.with(CardSpecification.distinct());
        addTypesToFilter(builder, cardFilter);
        addColorsToFilter(builder, cardFilter);
        addTagsToFilter(builder, cardFilter);
        addRaritiesToFilter(builder, cardFilter);
        addProductsToFilter(builder, cardFilter);
        addCostsToFilter(builder, cardFilter);
        addPowersToFilter(builder, cardFilter);
        addKeywordToFilter(builder, cardFilter);
        Page<CardEntity> results = cardDao.findAll(builder.build(), pageable);

        return new PageImpl<>(
                results.getContent()
                        .stream()
                        .map(cardEntity -> new Card(cardEntity, languageCode))
                        .collect(Collectors.toList()),
                pageable, results.getTotalElements());
    }

    private void addKeywordToFilter(SpecificationBuilder<CardEntity> builder, CardFilter cardFilter) {
        if (cardFilter.getKeyword() != null && !cardFilter.getKeyword().isEmpty()) {
            builder.with(CardSpecification.byKeyword(cardFilter.getKeyword()));
        }
    }

    private void addPowersToFilter(SpecificationBuilder<CardEntity> builder, CardFilter cardFilter) {
        if (cardFilter.getPowers() != null && !cardFilter.getPowers().isEmpty()) {
            builder.with(CardSpecification.byPower(cardFilter.getPowers()));
        }
    }

    private void addCostsToFilter(SpecificationBuilder<CardEntity> builder, CardFilter cardFilter) {
        if (cardFilter.getCosts() != null && !cardFilter.getCosts().isEmpty()) {
            builder.with(CardSpecification.byCost(cardFilter.getCosts()));
        }
    }

    private void addProductsToFilter(SpecificationBuilder<CardEntity> builder, CardFilter cardFilter) {
        if (cardFilter.getProducts() != null && !cardFilter.getProducts().isEmpty()) {
            builder.with(CardSpecification.byProductId(cardFilter.getProducts()
                    .stream().map(Product::getId)
                    .collect(Collectors.toSet())));
        }
    }

    private void addRaritiesToFilter(SpecificationBuilder<CardEntity> builder, CardFilter cardFilter) {
        if (cardFilter.getRarities() != null && !cardFilter.getRarities().isEmpty()) {
            builder.with(CardSpecification.byRarity(cardFilter.getRarities()
                    .stream().map(Rarity::getId)
                    .collect(Collectors.toSet())));
        }
    }

    private void addTagsToFilter(SpecificationBuilder<CardEntity> builder, CardFilter cardFilter) {
        if (cardFilter.getTags() != null && !cardFilter.getTags().isEmpty()) {
            builder.with(CardSpecification.byTagId(cardFilter.getTags()
                    .stream().map(Tag::getId)
                    .collect(Collectors.toSet())));
        }
    }

    private void addColorsToFilter(SpecificationBuilder<CardEntity> builder, CardFilter cardFilter) {
        if (cardFilter.getColors() != null && !cardFilter.getColors().isEmpty()) {
            builder.with(CardSpecification.byColorId(cardFilter.getColors()
                    .stream().map(Color::getId)
                    .collect(Collectors.toSet())));
        }
    }

    private void addTypesToFilter(SpecificationBuilder<CardEntity> builder,
                                  CardFilter cardFilter) {
        if (cardFilter.getTypes() != null && !cardFilter.getTypes().isEmpty()) {
            builder.with(CardSpecification.byTypeId(cardFilter.getTypes()
                    .stream().map(Type::getId)
                    .collect(Collectors.toSet())));
        }
    }

}
