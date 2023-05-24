package com.example.furnifactory.order;

import com.example.furnifactory.material.Material;
import com.example.furnifactory.util.EntitySpecification;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderSpecification implements EntitySpecification<Order> {

    private OrderStatus status;

    public Specification<Order> filterByStatus() {
        return (root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get("status"), status);
    }
    @Override
    public Specification<Order> getFilter() {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (status != null) {
                predicates.add(filterByStatus().toPredicate(root, query, criteriaBuilder));
            }
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
    }

}
