package com.opcgdb_api.service;

import com.opcgdb_api.dto.Card;
import com.opcgdb_api.entity.CardEntity;
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
                           Set<Long> typesId,
                           Set<Long> colorsId,
                           Set<Long> tagsId,
                           Set<Long> raritiesId,
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
        addTypesToFilter(builder, typesId);
        addColorsToFilter(builder, colorsId);
        addTagsToFilter(builder, tagsId);
        addRaritiesToFilter(builder, raritiesId);
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

    private void addRaritiesToFilter(SpecificationBuilder<CardEntity> builder, Set<Long> raritiesId) {
        if (raritiesId != null && !raritiesId.isEmpty()) {
            builder.with(CardSpecification.byRarity(raritiesId));
        }
    }

    private void addTagsToFilter(SpecificationBuilder<CardEntity> builder, Set<Long> tagsId) {
        if (tagsId != null && !tagsId.isEmpty()) {
            builder.with(CardSpecification.byTagId(tagsId));
        }
    }

    private void addColorsToFilter(SpecificationBuilder<CardEntity> builder, Set<Long> colorsId) {
        if (colorsId != null && !colorsId.isEmpty()) {
            builder.with(CardSpecification.byColorId(colorsId));
        }
    }

    private void addTypesToFilter(SpecificationBuilder<CardEntity> builder,
                                  Set<Long> typesId) {
        if (typesId != null && !typesId.isEmpty()) {
            builder.with(CardSpecification.byTypeId(typesId));
        }
    }

}
