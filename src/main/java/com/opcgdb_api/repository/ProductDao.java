package com.opcgdb_api.repository;

import com.opcgdb_api.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductDao extends JpaRepository<ProductEntity, String>, JpaSpecificationExecutor<ProductEntity> {

}
