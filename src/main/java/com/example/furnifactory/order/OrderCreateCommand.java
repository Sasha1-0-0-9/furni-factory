package com.example.furnifactory.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCreateCommand {
    private Long furnitureId;
    private Long userId;
    private int quantity;
    private String deliveryAddress;

    public OrderCreateCommand(Long furnitureId, Long userId, int quantity, double price, String deliveryAddress) {
        this.furnitureId = furnitureId;
        this.userId = userId;
        this.quantity = quantity;
        this.deliveryAddress = deliveryAddress;
    }

}
