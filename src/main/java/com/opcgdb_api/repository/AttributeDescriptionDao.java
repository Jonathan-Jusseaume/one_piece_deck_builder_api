package com.opcgdb_api.repository;

import com.opcgdb_api.entity.AttributeDescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AttributeDescriptionDao extends JpaRepository<AttributeDescriptionEntity, String>, JpaSpecificationExecutor<AttributeDescriptionEntity> {

}
