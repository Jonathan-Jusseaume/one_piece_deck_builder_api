package com.opcgdb_api.repository;

import com.opcgdb_api.entity.TagDescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TagDescriptionDao extends JpaRepository<TagDescriptionEntity, String>, JpaSpecificationExecutor<TagDescriptionEntity> {

}
