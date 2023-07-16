package com.opcgdb_api.repository;

import com.opcgdb_api.entity.CompetitiveStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CompetitiveStatusDao extends JpaRepository<CompetitiveStatusEntity, Long>, JpaSpecificationExecutor<CompetitiveStatusEntity> {

}
