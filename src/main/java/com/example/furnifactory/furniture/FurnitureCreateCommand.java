package com.example.furnifactory.furniture;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class FurnitureCreateCommand {
    private FurnitureType furnitureType;
    private int height;
    private int width;
    private int length;
    private int upperSurfaceLength;
    private int upperSurfaceWidth;
    private int upperSurfaceHeight;
    private Set<Long> materialIds;
}
