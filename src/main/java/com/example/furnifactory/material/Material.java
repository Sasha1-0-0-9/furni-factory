package com.example.furnifactory.material;

import com.example.furnifactory.manufacturer.Manufacturer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String material_name;
    private String description;

    @Enumerated(EnumType.STRING)
    private Color color;
    private long priceForSquareMeter;

    @OneToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    public Material mapPrimitives(MaterialCreateCommand command) {
        this.material_name = command.getName();
        this.description = command.getDescription();
        this.color = command.getColor();
        this.priceForSquareMeter = command.getPriceForSquareMeter();
        return this;
    }


}
