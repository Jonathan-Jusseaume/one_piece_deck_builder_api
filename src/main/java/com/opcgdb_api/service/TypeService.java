package com.opcgdb_api.service;

import com.opcgdb_api.dto.Type;
import com.opcgdb_api.repository.TypeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TypeService {

    private final TypeDao typeDao;

    public List<Type> list(String languageCode) {
        return typeDao.findAll()
                .stream()
                .map(typeEntity -> new Type(typeEntity, languageCode))
                .collect(Collectors.toList());
    }

}
