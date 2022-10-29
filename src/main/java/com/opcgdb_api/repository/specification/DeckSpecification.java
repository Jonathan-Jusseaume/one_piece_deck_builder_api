package com.opcgdb_api.repository.specification;

import com.opcgdb_api.entity.DeckEntity;
import org.springframework.data.jpa.domain.Specification;

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
