package com.example.furnifactory.order;

import com.example.furnifactory.furniture.Furniture;
import com.example.furnifactory.furniture.FurnitureRepository;
import com.example.furnifactory.user.User;
import com.example.furnifactory.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    private final FurnitureRepository furnitureRepository;
    private final UserRepository userRepository;

    public OrderDto create(Long furnitureId, OrderCreateCommand command) {
        Furniture furniture = furnitureRepository.findById(furnitureId)
                .orElseThrow(() -> new IllegalArgumentException("Furniture with id" + command.getFurnitureId() + " not found"));
        double price = furniture.getPrice() * command.getQuantity();

        User user = userRepository.findById(command.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User with id" + command.getUserId() + " not found"));
        Order order = new Order();
        order.setFurniture(furniture);
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);
        order.setQuantity(command.getQuantity());
        order.setPrice(price);
        order.setDeliveryAddress(command.getDeliveryAddress());
        Order savedOrder = orderRepository.save(order);
        return new OrderDto(savedOrder);
    }
}
