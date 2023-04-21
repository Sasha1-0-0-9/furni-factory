package com.example.furnifactory.furniture;

import com.example.furnifactory.material.Material;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class FurnitureDto {
    private Long id;
    private FurnitureType furnitureType;
    private Set<Material> materials;
    private double price;
    private double height;
    private double width;
    private double length;

    public FurnitureDto(Furniture furniture) {
        this.id = furniture.getId();
        this.furnitureType = furniture.getFurnitureType();
        this.materials = furniture.getMaterials();
        this.price = furniture.getPrice();
        this.height = furniture.getHeight();
        this.width = furniture.getWidth();
        this.length = furniture.getLength();
    }
}
