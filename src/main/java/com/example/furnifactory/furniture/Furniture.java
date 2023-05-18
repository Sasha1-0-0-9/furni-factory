package com.example.furnifactory.furniture;

import com.example.furnifactory.material.Material;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Furniture {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private FurnitureType furnitureType;
    @ManyToMany
    @JoinTable(name = "furniture_materials",
            joinColumns = {@JoinColumn(name = "furniture_id")},
            inverseJoinColumns = {@JoinColumn(name = "material_id")})
    private Set<Material> materials;
    private double price;
    private int height;
    private int width;
    private int length;
    private int upperSurfaceLength;
    private int upperSurfaceWidth;
    private int upperSurfaceHeight;
    private long createdBy;

    public Furniture mapPrimitives(FurnitureCreateCommand command) {
        this.furnitureType = command.getFurnitureType();
        this.height = command.getHeight();
        this.width = command.getWidth();
        this.length = command.getLength();
        this.upperSurfaceLength = command.getUpperSurfaceLength();
        this.upperSurfaceHeight = command.getUpperSurfaceHeight();
        this.upperSurfaceWidth = command.getUpperSurfaceWidth();
        return this;
    }
}

