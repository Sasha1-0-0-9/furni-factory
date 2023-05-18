package com.example.furnifactory.material;

import com.example.furnifactory.manufacturer.Manufacturer;
import com.example.furnifactory.manufacturer.ManufacturerRepository;
import com.example.furnifactory.manufacturer.ManufacturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MaterialService {
    private final MaterialRepository materialRepository;
    private final ManufacturerRepository manufacturerRepository;

    public List<MaterialDto> getAll(MaterialSpecification specification) {
        return materialRepository.findAll(specification.getFilter()).stream()
                .map(MaterialDto::new)
                .collect(Collectors.toList());
    }

    public Material save(Material material) {
        return materialRepository.save(material);
    }

    @Transactional
    public MaterialDto create(MaterialCreateCommand command) {
        Material material = new Material();
        material.mapPrimitives(command);
        Optional<Manufacturer> manufacturer = manufacturerRepository.findById(command.getManufacturerId());
        if(manufacturer.isEmpty()){
            throw new RuntimeException("Manufacturer not found");
        }
        material.setManufacturer(manufacturer.get());
        return new MaterialDto(save(material));
    }

    @Transactional
    public void delete(Long id) {
        materialRepository.deleteById(id);
    }

    public MaterialDto getById(Long id) {
        return new MaterialDto(materialRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Material not found")));
    }

    public Set<Material> getMaterialsByIds(Set<Long> ids) {
        return new HashSet<>(materialRepository.findAllById(ids));
    }
}
