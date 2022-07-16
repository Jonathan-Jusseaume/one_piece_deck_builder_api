package com.opcgdb_api.repository;

import com.opcgdb_api.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TagDao extends JpaRepository<TagEntity, Long>, JpaSpecificationExecutor<TagEntity> {

}
