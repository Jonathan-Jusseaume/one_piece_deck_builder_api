package com.opcgdb_api.repository;

import com.opcgdb_api.entity.DeckEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DeckDao extends JpaRepository<DeckEntity, String>, JpaSpecificationExecutor<DeckEntity> {

}
