package com.example.furnifactory.material;

import com.example.furnifactory.manufacturer.Manufacturer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaterialDto {
    private Long id;
    private String name;
    private String description;
    private Color color;
    private long priceForSquareMeter;
    private Manufacturer manufacturer;
    private String countryOfOrigin;

    public MaterialDto(Material material) {
        this.id = material.getId();
        this.name = material.getMaterial_name();
        this.description = material.getDescription();
        this.color = material.getColor();
        this.priceForSquareMeter = material.getPriceForSquareMeter();
        this.manufacturer = material.getManufacturer();
        //  this.countryOfOrigin = material.getCountryOfOrigin();
    }
}
