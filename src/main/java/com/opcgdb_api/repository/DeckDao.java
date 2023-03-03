package com.opcgdb_api.repository;

import com.opcgdb_api.entity.DeckEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface DeckDao extends JpaRepository<DeckEntity, UUID>, JpaSpecificationExecutor<DeckEntity> {

    Page<DeckEntity> findAll(Specification<DeckEntity> build, Pageable pageable, Sort sort);
}
