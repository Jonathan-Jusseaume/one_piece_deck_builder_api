package com.opcgdb_api.service;

import com.opcgdb_api.dto.Attribute;
import com.opcgdb_api.dto.Card;
import com.opcgdb_api.repository.AttributeDao;
import com.opcgdb_api.repository.CardDao;
import lombok.RequiredArgsConstructor;
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

}
