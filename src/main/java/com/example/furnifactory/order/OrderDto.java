package com.example.furnifactory.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private Long furnitureId;
    private Long userId;
    private OrderStatus status;
    private int quantity;
    private double price;
    private String deliveryAddress;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.furnitureId = order.getFurniture().getId();
        this.userId = order.getUser().getId();
        this.status = order.getStatus();
        this.quantity = order.getQuantity();
        this.deliveryAddress = order.getDeliveryAddress();
    }
}
