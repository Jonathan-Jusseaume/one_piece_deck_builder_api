package com.opcgdb_api.repository;

import com.opcgdb_api.entity.AttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AttributeDao extends JpaRepository<AttributeEntity, Long>, JpaSpecificationExecutor<AttributeEntity> {

}
