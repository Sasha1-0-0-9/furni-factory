package com.example.furnifactory.furniture;

import com.example.furnifactory.material.Material;
import com.example.furnifactory.material.MaterialRepository;
import com.example.furnifactory.material.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FurnitureService {
    private final FurnitureRepository furnitureRepository;
    private final MaterialService materialService;
    private final MaterialRepository materialRepository;

    //filters by user, order creation, etc

    public Furniture save(Furniture furniture) {
        return furnitureRepository.save(furniture);
    }

    public FurnitureDto create(FurnitureCreateCommand command) {
        Furniture furniture = new Furniture();
        furniture.mapPrimitives(command);
        Set<Material> materialSet = new HashSet<>();
        command.getMaterialIds()
                .forEach(id -> {
                    materialSet.add(materialRepository.findById(id).orElseThrow());
                });
        furniture.setMaterials(materialSet);
        furniture.setPrice(calculatePrice(furniture));
        return new FurnitureDto(save(furniture));
    }

    public void delete(Long id) {
        furnitureRepository.deleteById(id);
    }

    public List<FurnitureDto> getAll() {
        return furnitureRepository.findAll().stream()
                .map(FurnitureDto::new)
                .collect(Collectors.toList());
    }

    private double calculatePrice(Furniture furniture) {
        double price = 0.0;
        for (Material material : furniture.getMaterials()) {
            price += material.getPriceForSquareMeter() * (furniture.getHeight() * furniture.getWidth() * furniture.getLength());
        }
        //todo custom furniture type price calculation
        return price;
    }
}
