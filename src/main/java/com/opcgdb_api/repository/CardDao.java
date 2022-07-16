package com.opcgdb_api.repository;

import com.opcgdb_api.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CardDao extends JpaRepository<CardEntity, String>, JpaSpecificationExecutor<CardEntity> {

}
