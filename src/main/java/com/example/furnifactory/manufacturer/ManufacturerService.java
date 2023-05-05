package com.example.furnifactory.manufacturer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerDto createManufacturer(ManufacturerCreateCommand command) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setCompany_name(command.getCompany_name());
        manufacturer.setCountry(command.getCountry());
        manufacturer.setCity(command.getCity());
        manufacturer.setStreet(command.getStreet());
        manufacturerRepository.save(manufacturer);
        return new ManufacturerDto(manufacturer);
    }

    public List<ManufacturerDto> getAll() {
        return manufacturerRepository.findAll()
                .stream()
                .map(ManufacturerDto::new)
                .collect(Collectors.toList());
    }

    public ManufacturerDto getById(Long id) {
        return manufacturerRepository.findById(id)
                .map(ManufacturerDto::new)
                .orElseThrow(() -> new RuntimeException("Manufacturer not found"));
    }

    public ManufacturerDto updateManufacturer(Long id, ManufacturerCreateCommand command) {
        Manufacturer manufacturer = manufacturerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manufacturer not found"));
        manufacturer.setCompany_name(command.getCompany_name());
        manufacturer.setCountry(command.getCountry());
        manufacturer.setCity(command.getCity());
        manufacturer.setStreet(command.getStreet());
        manufacturerRepository.save(manufacturer);
        return new ManufacturerDto(manufacturer);
    }

    public void deleteById(Long id) {
        Manufacturer manufacturer = manufacturerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manufacturer not found"));
        manufacturerRepository.deleteById(id);
    }
}
