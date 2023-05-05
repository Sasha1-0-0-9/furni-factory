package com.example.furnifactory.user;

import com.example.furnifactory.util.EntitySpecification;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification implements EntitySpecification<User> {

    @Override
    public Specification<User> getFilter() {
        return null;
    }

}
