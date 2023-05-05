package com.example.furnifactory.manufacturer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManufacturerCreateCommand {
    private String company_name;
    private String country;
    private String city;
    private String street;
}
