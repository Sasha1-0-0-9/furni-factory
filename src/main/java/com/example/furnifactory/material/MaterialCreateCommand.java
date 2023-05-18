package com.example.furnifactory.material;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaterialCreateCommand {
    private String name;
    private String description;
    private Color color;
    private long priceForSquareMeter;
    private Long manufacturerId;
}
