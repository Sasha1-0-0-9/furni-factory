package com.example.furnifactory.order;

import com.example.furnifactory.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private Long furnitureId;
    private UserDto user;
    private OrderStatus status;
    private int quantity;
    private double price;
    private String deliveryAddress;
    private LocalDateTime createdAt;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.furnitureId = order.getFurniture().getId();
        this.user = UserDto.from(order.getUser());
        this.status = order.getStatus();
        this.quantity = order.getQuantity();
        this.deliveryAddress = order.getDeliveryAddress();
        this.createdAt = order.getCreatedAt();
    }
}
