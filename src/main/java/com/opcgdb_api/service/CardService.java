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

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardDao cardDao;

    public Page<Card> list(Pageable pageable,
                           Set<String> productsId,
                           Set<Integer> costs,
                           Set<Integer> powers,
                           String keyword,
                           String languageCode) {
        if (pageable == null) {
            pageable = Pageable.ofSize(25);
        }
        SpecificationBuilder<CardEntity> builder = new SpecificationBuilder<>();
        builder.with(CardSpecification.distinct());
      /*  addTypesToFilter(builder, cardFilter);
        addColorsToFilter(builder, cardFilter);
        addTagsToFilter(builder, cardFilter);
        addRaritiesToFilter(builder, cardFilter); */
        addProductsToFilter(builder, productsId);
        addCostsToFilter(builder, costs);
        addPowersToFilter(builder, powers);
        addKeywordToFilter(builder, keyword);
        Page<CardEntity> results = cardDao.findAll(builder.build(), pageable);
        return new PageImpl<>(
                results.getContent()
                        .stream()
                        .map(cardEntity -> new Card(cardEntity, languageCode))
                        .collect(Collectors.toList()),
                pageable, results.getTotalElements());
    }

    private void addKeywordToFilter(SpecificationBuilder<CardEntity> builder, String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            builder.with(CardSpecification.byKeyword(keyword));
        }
    }

    private void addPowersToFilter(SpecificationBuilder<CardEntity> builder, Set<Integer> powers) {
        if (powers != null && !powers.isEmpty()) {
            builder.with(CardSpecification.byPower(powers));
        }
    }

    private void addCostsToFilter(SpecificationBuilder<CardEntity> builder, Set<Integer> costs) {
        if (costs != null && !costs.isEmpty()) {
            builder.with(CardSpecification.byCost(costs));
        }
    }

    private void addProductsToFilter(SpecificationBuilder<CardEntity> builder, Set<String> productsId) {
        if (productsId != null && !productsId.isEmpty()) {
            builder.with(CardSpecification.byProductId(productsId));
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
