package com.opcgdb_api.repository;

import com.opcgdb_api.entity.DeckEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface DeckDao extends JpaRepository<DeckEntity, UUID>, JpaSpecificationExecutor<DeckEntity> {

}
