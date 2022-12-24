package com.opcgdb_api.service;

import com.opcgdb_api.dto.Tag;
import com.opcgdb_api.repository.TagDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TagService {

    private final TagDao tagDao;

    public List<Tag> list(String languageCode) {
        return tagDao.findAll()
                .stream()
                .map(tagEntity -> new Tag(tagEntity, languageCode))
                .sorted()
                .collect(Collectors.toList());
    }

}
