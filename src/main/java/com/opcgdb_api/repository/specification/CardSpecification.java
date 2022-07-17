package com.opcgdb_api.repository.specification;

import com.opcgdb_api.dto.Card;
import com.opcgdb_api.entity.CardEntity;
import com.opcgdb_api.entity.ColorEntity;
import com.opcgdb_api.entity.TagEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.SetJoin;
import java.util.Set;

public class CardSpecification {

    public static Specification<CardEntity> byTypeId(Set<Long> typesId) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                .in(root.get("type").get("id"))
                .value(typesId);
    }

    public static Specification<CardEntity> byColorId(Set<Long> colorsId) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            SetJoin<CardEntity, ColorEntity> join = root.joinSet("colors");
            return join.get("id").in(colorsId);
        });
    }

    public static Specification<CardEntity> byTagId(Set<Long> tagsId) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            SetJoin<CardEntity, TagEntity> join = root.joinSet("tags");
            return join.get("id").in(tagsId);
        });
    }

}
