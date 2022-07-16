package com.opcgdb_api.repository;

import com.opcgdb_api.entity.ColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ColorDao extends JpaRepository<ColorEntity, Long>, JpaSpecificationExecutor<ColorEntity> {

}
