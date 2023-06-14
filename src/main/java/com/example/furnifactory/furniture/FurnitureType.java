package com.example.furnifactory.furniture;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum FurnitureType {
    WARDROBE(2, 4,1),
    SHELF(4,2, 4);

    private int number_of_door_handles;
    private double priceForHandle;
    private double additionalResourcesPrice;


    FurnitureType(int number_of_door_handles, double priceForHandle, double additionalResourcesPrice) {
        this.number_of_door_handles = number_of_door_handles;
        this.priceForHandle = priceForHandle;
        this.additionalResourcesPrice = additionalResourcesPrice;
    }
}
