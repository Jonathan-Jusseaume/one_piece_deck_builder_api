package com.opcgdb_api.repository.specification;

import com.opcgdb_api.entity.*;
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
            SetJoin<CardEntity, CardDescriptionEntity> join = root.joinSet(SpecificationKeyEnum.CARD_DESCRIPTION.toString());
            Predicate predicate = null;
            for (String word : keyword.split(" ")) {
                Predicate predicateWord;
                if (word.length() > 1 && word.charAt(0) == '!') {
                    predicateWord =
                            criteriaBuilder.and(
                                    criteriaBuilder.or(
                                            criteriaBuilder.isNull(join.get(SpecificationKeyEnum.EFFECT.toString()))
                                            , criteriaBuilder.not(
                                                    criteriaBuilder.like(criteriaBuilder.lower(join.get(SpecificationKeyEnum.EFFECT.toString())),
                                                            "%" + word.substring(1).toLowerCase() + "%")
                                            )
                                    ),
                                    criteriaBuilder.not(criteriaBuilder.like(criteriaBuilder.lower(join.get(SpecificationKeyEnum.NAME.toString())),
                                            "%" + word.substring(1).toLowerCase() + "%")));
                } else {
                    predicateWord =
                            criteriaBuilder.or(
                                    criteriaBuilder.like(criteriaBuilder.lower(join.get(SpecificationKeyEnum.EFFECT.toString())), "%" + word.toLowerCase() + "%"),
                                    criteriaBuilder.like(criteriaBuilder.lower(join.get(SpecificationKeyEnum.NAME.toString())), "%" + word.toLowerCase() + "%"));
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
                .in(root.get(SpecificationKeyEnum.TYPE.toString())
                        .get(SpecificationKeyEnum.ID.toString()))
                .value(typesId);
    }

    public static Specification<CardEntity> byColorId(Set<Long> colorsId) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            SetJoin<CardEntity, ColorEntity> join = root.joinSet(SpecificationKeyEnum.COLOR.toString());
            return join.get(SpecificationKeyEnum.ID.toString()).in(colorsId);
        });
    }

    public static Specification<CardEntity> byTagId(Set<Long> tagsId) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            SetJoin<CardEntity, TagEntity> join = root.joinSet(SpecificationKeyEnum.TAG.toString());
            return join.get(SpecificationKeyEnum.ID.toString()).in(tagsId);
        });
    }

    public static Specification<CardEntity> byRarity(Set<Long> rarityId) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            SetJoin<CardEntity, CardImageEntity> join = root.joinSet(SpecificationKeyEnum.IMAGE.toString());
            return join.get(SpecificationKeyEnum.RARITY.toString()).get(SpecificationKeyEnum.ID.toString()).in(rarityId);
        });
    }

    public static Specification<CardEntity> byCost(Set<Integer> costs) {
        return ((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.and(
                        criteriaBuilder.isNotNull(root.get(SpecificationKeyEnum.COST.toString())),
                        criteriaBuilder.in(root.get(SpecificationKeyEnum.COST.toString())).value(costs)));
    }


    public static Specification<CardEntity> byPower(Set<Integer> powers) {
        return ((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.and(
                        criteriaBuilder.isNotNull(root.get(SpecificationKeyEnum.POWER.toString())),
                        criteriaBuilder.in(root.get(SpecificationKeyEnum.POWER.toString())).value(powers)));
    }

    public static Specification<CardEntity> byProductId(Set<String> productsId) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            SetJoin<CardEntity, CardImageEntity> join = root.joinSet(SpecificationKeyEnum.IMAGE.toString());
            return join.get(SpecificationKeyEnum.PRODUCT.toString())
                    .get(SpecificationKeyEnum.ID.toString())
                    .in(productsId);
        });
    }

    public static Specification<CardEntity> byCompetitiveStatusId(Set<Long> competitiveStatusId) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                .in(root.get(SpecificationKeyEnum.COMPETITIVE_STATUS.toString())
                        .get(SpecificationKeyEnum.ID.toString()))
                .value(competitiveStatusId);
    }
}
