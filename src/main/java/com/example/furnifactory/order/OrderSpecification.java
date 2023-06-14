package com.example.furnifactory.order;

import com.example.furnifactory.material.Material;
import com.example.furnifactory.util.EntitySpecification;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderSpecification implements EntitySpecification<Order> {

    private OrderStatus status;

    private LocalDateTime createdAtFrom;
    private LocalDateTime createdAtTo;

    public Specification<Order> filterByStatus() {
        return (root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get("status"), status);
    }

    public Specification<Order> filterByCreatedAt() {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (createdAtFrom != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), createdAtFrom));
            }
            if (createdAtTo != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), createdAtTo));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Override
    public Specification<Order> getFilter() {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (status != null) {
                predicates.add(filterByStatus().toPredicate(root, query, criteriaBuilder));
            }
            if (createdAtFrom != null || createdAtTo != null) {
                predicates.add(filterByCreatedAt().toPredicate(root, query, criteriaBuilder));
            }
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
    }

}
