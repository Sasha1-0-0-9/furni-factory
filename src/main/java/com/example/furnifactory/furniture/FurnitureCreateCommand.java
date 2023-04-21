package com.example.furnifactory.furniture;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class FurnitureCreateCommand {
    private FurnitureType furnitureType;
    private double height;
    private double width;
    private double length;
    private Set<Long> materialIds;
}
