package com.example.furnifactory.furniture;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum FurnitureType {
    WARDROBE(2),
    SHELF(4);

    private int number_of_door_handles;


    FurnitureType(int number_of_door_handles) {
        this.number_of_door_handles = number_of_door_handles;
    }
}
