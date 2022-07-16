package com.opcgdb_api.repository;

import com.opcgdb_api.entity.RarityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RarityDao extends JpaRepository<RarityEntity, Long>, JpaSpecificationExecutor<RarityEntity> {

}
