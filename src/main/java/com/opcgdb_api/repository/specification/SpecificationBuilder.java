package com.opcgdb_api.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SpecificationBuilder<T> {

    private final List<Specification<T>> specifications;

    public SpecificationBuilder() {
        this.specifications = new ArrayList<>();
    }


    public final SpecificationBuilder<T> with(Specification<T> specification) {
        specifications.add(specification);
        return this;
    }

    public Specification<T> build() {
        Specification<T> result = null;
        if (!specifications.isEmpty()) {
            int index = 0;
            result = specifications.get(index++);
            for (; index < specifications.size(); ++index) {
                result = Specification.where(result).and(specifications.get(index));
            }
        }
        return result;
    }

}

