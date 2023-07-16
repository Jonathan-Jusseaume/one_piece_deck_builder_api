package com.opcgdb_api.service;

import com.opcgdb_api.dto.CompetitiveStatus;
import com.opcgdb_api.repository.CompetitiveStatusDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CompetitiveStatusService {

    private final CompetitiveStatusDao competitiveStatusDao;

    public List<CompetitiveStatus> list(String languageCode) {
        return competitiveStatusDao.findAll()
                .stream()
                .map(competitiveStatusEntity -> new CompetitiveStatus(competitiveStatusEntity, languageCode))
                .collect(Collectors.toList());
    }

}
