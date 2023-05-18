package com.example.furnifactory.order;

import com.example.furnifactory.util.EntitySpecification;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
public class OrderSpecification implements EntitySpecification<Order> {

    private OrderStatus status;
    @Override
    public Specification<Order> getFilter() {
        return null;
    }

}
