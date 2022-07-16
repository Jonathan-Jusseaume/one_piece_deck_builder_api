package com.opcgdb_api.service;

import com.opcgdb_api.dto.Attribute;
import com.opcgdb_api.repository.AttributeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttributeService {

    private final AttributeDao attributeDao;

    public List<Attribute> list(String languageCode) {
        return attributeDao.findAll()
                .stream()
                .map(attributeEntity -> new Attribute(attributeEntity, languageCode))
                .collect(Collectors.toList());
    }

}
