package com.opcgdb_api.repository.specification;

import com.opcgdb_api.entity.CardDescriptionEntity;
import com.opcgdb_api.entity.CardEntity;
import com.opcgdb_api.entity.ColorEntity;
import com.opcgdb_api.entity.TagEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.SetJoin;
import java.util.Set;

public class CardSpecification {

    private CardSpecification() throws InstantiationException {
        throw new InstantiationException("Can't instantiate CardSpecification");
    }

    public static Specification<CardEntity> distinct() {
        return (root, query, cb) -> {
            query.distinct(true);
            return null;
        };
    }

    public static Specification<CardEntity> byKeyword(String keyword) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            SetJoin<CardEntity, CardDescriptionEntity> join = root.joinSet("descriptions");
            Predicate predicate = null;
            for (String word : keyword.split(" ")) {
                Predicate predicateWord;
                if (word.length() > 1 && word.charAt(0) == '!') {
                    predicateWord =
                            criteriaBuilder.and(
                                    criteriaBuilder.or(
                                            criteriaBuilder.isNull(join.get("effect"))
                                            , criteriaBuilder.not(
                                                    criteriaBuilder.like(criteriaBuilder.lower(join.get("effect")),
                                                            "%" + word.substring(1).toLowerCase() + "%")
                                            )
                                    ),
                                    criteriaBuilder.not(criteriaBuilder.like(criteriaBuilder.lower(join.get("name")),
                                            "%" + word.substring(1).toLowerCase() + "%")));
                } else {
                    predicateWord =
                            criteriaBuilder.or(
                                    criteriaBuilder.like(criteriaBuilder.lower(join.get("effect")), "%" + word.toLowerCase() + "%"),
                                    criteriaBuilder.like(criteriaBuilder.lower(join.get("name")), "%" + word.toLowerCase() + "%"));
                }

                if (predicate != null) {
                    predicate = criteriaBuilder.and(predicate, predicateWord);
                } else {
                    predicate = predicateWord;
                }
            }
            return predicate;
        });
    }

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

    public static Specification<CardEntity> byRarity(Set<Long> rarityId) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.in(root.get("rarity").get("id")).value(rarityId));
    }

    public static Specification<CardEntity> byCost(Set<Integer> costs) {
        return ((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.and(
                        criteriaBuilder.isNotNull(root.get("cost")),
                        criteriaBuilder.in(root.get("cost")).value(costs)));
    }


    public static Specification<CardEntity> byPower(Set<Integer> powers) {
        return ((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.and(
                        criteriaBuilder.isNotNull(root.get("power")),
                        criteriaBuilder.in(root.get("power")).value(powers)));
    }

    public static Specification<CardEntity> byProductId(Set<String> productsId) {
        return ((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.in(root.get("product").get("id")).value(productsId));
    }
}
