package com.opcgdb_api.repository.specification;

import com.opcgdb_api.entity.CardEntity;
import com.opcgdb_api.entity.ColorEntity;
import com.opcgdb_api.entity.DeckEntity;
import com.opcgdb_api.entity.UserEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.SetJoin;
import java.util.Set;

public class DeckSpecification {

    private DeckSpecification() throws InstantiationException {
        throw new InstantiationException("Can't instantiate DeckSpecification");
    }

    public static Specification<DeckEntity> distinct() {
        return (root, query, cb) -> {
            query.distinct(true);
            return null;
        };
    }

    public static Specification<DeckEntity> byUserMail(String mail) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("user").get("mail"), mail));
    }

    public static Specification<DeckEntity> byKeyword(String keyword) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = null;
            for (String word : keyword.split(" ")) {
                Predicate predicateWord;
                if (word.length() > 1 && word.charAt(0) == '!') {
                    predicateWord =
                            criteriaBuilder.and(
                                    criteriaBuilder.or(
                                            criteriaBuilder.isNull(root.get("description"))
                                            , criteriaBuilder.not(
                                                    criteriaBuilder.like(criteriaBuilder.lower(root.get("description")),
                                                            "%" + word.substring(1).toLowerCase() + "%")
                                            )
                                    ),
                                    criteriaBuilder.not(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                                            "%" + word.substring(1).toLowerCase() + "%")));
                } else {
                    predicateWord =
                            criteriaBuilder.or(
                                    criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + word.toLowerCase() + "%"),
                                    criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + word.toLowerCase() + "%"));
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

    public static Specification<DeckEntity> byColorId(Set<Long> colorsId) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            Join<DeckEntity, CardEntity> join = root.join("leader");
            SetJoin<CardEntity, ColorEntity> colorsJoin = join.joinSet("colors");
            return colorsJoin.get("id").in(colorsId);
        });
    }

    public static Specification<DeckEntity> byUserFavoriteDeck(String mail) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            SetJoin<DeckEntity, UserEntity> join = root.joinSet("usersFavorite");
            return join.get("mail").in(mail);
        });
    }
}
