package com.example.furnifactory.manufacturer;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//чисто для адмінки, щоб створювати виробників матеріалів.
// ніякої особливої ролі в проекті не має, додав її, щоб лити більше води в докі)))
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String company_name;
    private String country;
    private String city;
    private String street;
}
