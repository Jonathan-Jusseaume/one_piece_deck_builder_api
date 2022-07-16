package com.opcgdb_api.repository;

import com.opcgdb_api.entity.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TypeDao extends JpaRepository<TypeEntity, Long>, JpaSpecificationExecutor<TypeEntity> {

}
