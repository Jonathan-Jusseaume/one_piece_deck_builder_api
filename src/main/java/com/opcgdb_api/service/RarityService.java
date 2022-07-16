package com.opcgdb_api.service;

import com.opcgdb_api.dto.Rarity;
import com.opcgdb_api.repository.RarityDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RarityService {

    private final RarityDao rarityDao;

    public List<Rarity> list() {
        return rarityDao.findAll()
                .stream()
                .map(Rarity::new)
                .collect(Collectors.toList());
    }

}
