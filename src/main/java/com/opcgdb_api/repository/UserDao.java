package com.opcgdb_api.repository;

import com.opcgdb_api.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDao extends JpaRepository<UserEntity, String>, JpaSpecificationExecutor<UserEntity> {

}
