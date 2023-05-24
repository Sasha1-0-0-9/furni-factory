package com.example.furnifactory.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCreateCommand {
    private Long userId;
    private int quantity;
    private String deliveryAddress;

    public OrderCreateCommand(Long userId, int quantity, String deliveryAddress) {
        this.userId = userId;
        this.quantity = quantity;
        this.deliveryAddress = deliveryAddress;
    }

}
