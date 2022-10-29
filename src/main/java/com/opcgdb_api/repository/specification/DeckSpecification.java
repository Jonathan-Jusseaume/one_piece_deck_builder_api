package com.opcgdb_api.repository.specification;

import com.opcgdb_api.entity.*;
import org.springframework.data.jpa.domain.Specification;

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

}
