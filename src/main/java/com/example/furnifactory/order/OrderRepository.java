package com.example.furnifactory.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserIdAndFurnitureId(Long userId, Long furnitureId);

    boolean existsByUserIdAndFurnitureId(Long userId, Long furnitureId);

}
