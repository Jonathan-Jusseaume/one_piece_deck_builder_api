package com.opcgdb_api.repository;

import com.opcgdb_api.dto.Card;
import com.opcgdb_api.entity.CardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.OneToOne;

public interface CardDao extends JpaRepository<CardEntity, String>, JpaSpecificationExecutor<CardEntity> {

}
