package com.example.furnifactory.order;

import com.example.furnifactory.furniture.Furniture;
import com.example.furnifactory.furniture.FurnitureDto;
import com.example.furnifactory.furniture.FurnitureRepository;
import com.example.furnifactory.user.User;
import com.example.furnifactory.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    private final FurnitureRepository furnitureRepository;
    private final UserRepository userRepository;

    public OrderDto create(Long furnitureId, OrderCreateCommand command) {
        Furniture furniture = furnitureRepository.findById(furnitureId)
                .orElseThrow(() -> new IllegalArgumentException("Furniture with id" + furnitureId + " not found"));
        double price = furniture.getPrice() * command.getQuantity();

        User user = userRepository.findById(command.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User with id" + command.getUserId() + " not found"));
        Order order = new Order();
        order.setFurniture(furniture);
        order.setUser(user);
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        order.setQuantity(command.getQuantity());
        order.setPrice(price);
        order.setDeliveryAddress(command.getDeliveryAddress());
        Order savedOrder = orderRepository.save(order);
        return new OrderDto(savedOrder);
    }

    public List<OrderDto> orderAllFromCart(Long userId, OrderAllCommand command) {
        List<FurnitureDto> unordered = furnitureRepository.findAll().stream()
                .filter(furniture -> furniture.getCreatedBy() == userId)
                .filter(furniture -> orderRepository.findAllByUserIdAndFurnitureId(userId, furniture.getId()).isEmpty())
                .map(FurnitureDto::new)
                .collect(Collectors.toList());
        return unordered.stream().map(
                furniture -> create(furniture.getId(), new OrderCreateCommand(userId,1, command.getDeliveryAddress())))
                .collect(Collectors.toList());
    }

    public List<OrderDto> getPendings(OrderSpecification orderSpecification){
        return orderRepository.findAll(orderSpecification.getFilter()).stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
    }

    public OrderDto manage(Long orderId, ChangeOrderStatusCommand command) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order with id" + orderId + " not found"));
        order.setStatus(command.getStatus());
        Order savedOrder = orderRepository.save(order);
        return new OrderDto(savedOrder);
    }

    public List<OrderDto> getAll(OrderSpecification orderSpecification){
        return orderRepository.findAll(orderSpecification.getFilter()).stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
    }
}
