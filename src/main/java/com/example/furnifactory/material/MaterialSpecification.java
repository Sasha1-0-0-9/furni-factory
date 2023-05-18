package com.example.furnifactory.material;

import com.example.furnifactory.util.EntitySpecification;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MaterialSpecification implements EntitySpecification<Material> {
    private Long priceForSquareMeterFrom;
    private Long priceForSquareMeterTo;

    public Specification<Material> filterByPriceForSquareMeter() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.and(
                        criteriaBuilder.greaterThanOrEqualTo(root.get("priceForSquareMeter"), priceForSquareMeterFrom),
                        criteriaBuilder.lessThanOrEqualTo(root.get("priceForSquareMeter"), priceForSquareMeterTo)
                );
    }

    @Override
    public Specification<Material> getFilter() {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (priceForSquareMeterFrom != null && priceForSquareMeterTo != null) {
                predicates.add(filterByPriceForSquareMeter().toPredicate(root, query, criteriaBuilder));
            }
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));

        };

    }
}
