package com.opcgdb_api.service;

import com.opcgdb_api.dto.Color;
import com.opcgdb_api.repository.ColorDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ColorService {

    private final ColorDao colorDao;

    public List<Color> list(String languageCode) {
        return colorDao.findAll()
                .stream()
                .map(colorEntity -> new Color(colorEntity, languageCode))
                .collect(Collectors.toList());
    }

}
