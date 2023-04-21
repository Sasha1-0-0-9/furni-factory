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
    private FurnitureType furnitureType;
    @OneToMany
    private Set<Material> materials;
    private double price;
    private double height;
    private double width;
    private double length;

    public Furniture mapPrimitives(FurnitureCreateCommand command) {
        this.furnitureType = command.getFurnitureType();
        this.height = command.getHeight();
        this.width = command.getWidth();
        this.length = command.getLength();
        return this;
    }
}

