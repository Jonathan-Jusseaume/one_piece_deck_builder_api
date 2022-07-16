package com.opcgdb_api.repository;

import com.opcgdb_api.entity.ProductDescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductDescriptionDao extends JpaRepository<ProductDescriptionEntity, String>, JpaSpecificationExecutor<ProductDescriptionEntity> {

}
