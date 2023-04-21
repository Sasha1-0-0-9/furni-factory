package com.example.furnifactory.material;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MaterialService {
    private final MaterialRepository materialRepository;

    public List<MaterialDto> getAll() {
        return materialRepository.findAll().stream()
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
        //TODO set manufacturer
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
