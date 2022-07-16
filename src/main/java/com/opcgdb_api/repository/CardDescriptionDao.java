package com.opcgdb_api.repository;

import com.opcgdb_api.entity.CardDescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CardDescriptionDao extends JpaRepository<CardDescriptionEntity, String>, JpaSpecificationExecutor<CardDescriptionEntity> {

}
