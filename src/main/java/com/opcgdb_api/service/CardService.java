package com.opcgdb_api.service;

import com.opcgdb_api.dto.Card;
import com.opcgdb_api.dto.Color;
import com.opcgdb_api.dto.Tag;
import com.opcgdb_api.dto.Type;
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
import java.util.Set;
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
        if (cardFilter.getTypes() != null && !cardFilter.getTypes().isEmpty()) {
            builder.with(CardSpecification.byTypeId(cardFilter.getTypes()
                    .stream().map(Type::getId)
                    .collect(Collectors.toSet())));
        }
        if (cardFilter.getColors() != null && !cardFilter.getColors().isEmpty()) {
            builder.with(CardSpecification.byColorId(cardFilter.getColors()
                    .stream().map(Color::getId)
                    .collect(Collectors.toSet())));
        }
        if (cardFilter.getTags() != null && !cardFilter.getTags().isEmpty()) {
            builder.with(CardSpecification.byTagId(cardFilter.getTags()
                    .stream().map(Tag::getId)
                    .collect(Collectors.toSet())));
        }


        Page<CardEntity> results = cardDao.findAll(builder.build(), pageable);

        return new PageImpl<>(
                results.getContent()
                        .stream()
                        .map(cardEntity -> new Card(cardEntity, languageCode))
                        .collect(Collectors.toList()),
                pageable, results.getTotalElements());
    }
}
