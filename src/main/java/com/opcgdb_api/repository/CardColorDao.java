package com.opcgdb_api.repository;

import com.opcgdb_api.entity.CardColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CardColorDao extends JpaRepository<CardColorEntity, String>, JpaSpecificationExecutor<CardColorEntity> {

}
