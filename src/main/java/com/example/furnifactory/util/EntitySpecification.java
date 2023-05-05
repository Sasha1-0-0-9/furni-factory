package com.example.furnifactory.util;

import org.springframework.data.jpa.domain.Specification;

public interface EntitySpecification<T> {
    Specification<T> getFilter();
}
