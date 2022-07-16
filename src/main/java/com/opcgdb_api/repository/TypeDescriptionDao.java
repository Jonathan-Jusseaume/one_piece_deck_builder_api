package com.opcgdb_api.repository;

import com.opcgdb_api.entity.TypeDescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TypeDescriptionDao extends JpaRepository<TypeDescriptionEntity, String>, JpaSpecificationExecutor<TypeDescriptionEntity> {

}
