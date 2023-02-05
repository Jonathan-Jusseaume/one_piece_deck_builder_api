package com.opcgdb_api.service;

import com.opcgdb_api.dto.Attribute;
import com.opcgdb_api.entity.AttributeEntity;
import com.opcgdb_api.repository.AttributeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AttributeService {

    private final AttributeDao attributeDao;

    public List<Attribute> list(String languageCode) {
        return attributeDao.findAll()
                .stream()
                .map(attributeEntity -> new Attribute(attributeEntity, languageCode))
                .collect(Collectors.toList());
    }

    public Attribute read(Long attributeId, String languageCode) {
        Optional<AttributeEntity> optionalAttributeEntity = attributeDao.findById(attributeId);
        if (optionalAttributeEntity.isPresent()) {
            return new Attribute(optionalAttributeEntity.get(), languageCode);
        }
        return null;
    }
}
