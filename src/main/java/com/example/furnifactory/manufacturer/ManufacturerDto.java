package com.example.furnifactory.manufacturer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ManufacturerDto {
    private Long id;
    private String company_name;
    private String country;
    private String city;
    private String street;

    public ManufacturerDto(Manufacturer manufacturer) {
        this.id = manufacturer.getId();
        this.company_name = manufacturer.getCompany_name();
        this.country = manufacturer.getCountry();
        this.city = manufacturer.getCity();
        this.street = manufacturer.getStreet();
    }
}
