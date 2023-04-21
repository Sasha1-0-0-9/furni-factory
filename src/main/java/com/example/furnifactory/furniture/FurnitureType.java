package com.example.furnifactory.furniture;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum FurnitureType {
    WARDROBE(2, 50, 29),
    SHELF(4, 30, 20);

    private int number_of_doors;
    private double door_height;
    private double door_width;

    FurnitureType(int number_of_doors, int door_height, int door_width) {
        this.number_of_doors = number_of_doors;
        this.door_height = door_height;
        this.door_width = door_width;
    }
}
