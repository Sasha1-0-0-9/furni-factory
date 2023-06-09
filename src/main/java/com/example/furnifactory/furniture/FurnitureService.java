package com.example.furnifactory.furniture;

import com.example.furnifactory.material.Material;
import com.example.furnifactory.material.MaterialRepository;
import com.example.furnifactory.material.MaterialService;
import com.example.furnifactory.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.furnifactory.furniture.FurnitureType.SHELF;
import static com.example.furnifactory.furniture.FurnitureType.WARDROBE;

@Service
@RequiredArgsConstructor
public class FurnitureService {
    private final FurnitureRepository furnitureRepository;
    private final MaterialService materialService;
    private final MaterialRepository materialRepository;
    private final OrderRepository orderRepository;

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
        furniture.setCreatedBy(command.getCreatedBy());
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

    public List<FurnitureDto> getCart(Long userId) {
        return furnitureRepository.findAll().stream()
                .filter(furniture -> furniture.getCreatedBy() == userId)
                .filter(furniture -> orderRepository.findAllByUserIdAndFurnitureId(userId, furniture.getId()).isEmpty())
                .map(FurnitureDto::new)
                .collect(Collectors.toList());
    }

    public double getFurniturePrice(FurnitureCreateCommand command) {
        Furniture furniture = new Furniture();
        furniture.mapPrimitives(command);
        furniture.setMaterials(command.getMaterialIds().stream()
                .map(id -> materialRepository.findById(id).orElseThrow()).collect(Collectors.toSet()));
        return calculatePrice(furniture);
    }

    private double calculatePrice(Furniture furniture) {
        double price = 0.0;
        double surfaceArea = 2 * ((furniture.getWidth() / 100.0 * furniture.getHeight() / 100.0)
                + (furniture.getWidth() / 100.0 * furniture.getLength() / 100.0) + (furniture.getHeight() / 100.0 * furniture.getLength() / 100.0));
        for (Material material : furniture.getMaterials()) {
            price += material.getPriceForSquareMeter() * surfaceArea;
        }
        switch (furniture.getFurnitureType()) {
            case WARDROBE:
                price += furniture.getFurnitureType().getNumber_of_door_handles() * WARDROBE.getPriceForHandle();
                break;
            case SHELF:
                price += furniture.getFurnitureType().getNumber_of_door_handles() * SHELF.getPriceForHandle();
                break;
        }
        price += furniture.getFurnitureType().getAdditionalResourcesPrice();
        return price;
    }

    public double getFurnitureArea(FurnitureDto furniture){
        return 2 * ((furniture.getWidth() / 100.0 * furniture.getHeight() / 100.0)
                + (furniture.getWidth() / 100.0 * furniture.getLength() / 100.0) + (furniture.getHeight() / 100.0 * furniture.getLength() / 100.0));
    }

    public FurnitureDto get(Long id) {
        return new FurnitureDto(furnitureRepository.findById(id).orElseThrow());
    }
}
