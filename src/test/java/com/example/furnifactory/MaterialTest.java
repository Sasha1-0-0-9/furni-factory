package com.example.furnifactory;

import com.example.furnifactory.manufacturer.Manufacturer;
import com.example.furnifactory.manufacturer.ManufacturerRepository;
import com.example.furnifactory.material.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class MaterialTest {
    @Mock
    private MaterialRepository materialRepository;

    @Mock
    private ManufacturerRepository manufacturerRepository;

    @InjectMocks
    private MaterialService materialService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll_ReturnsListOfMaterialDto() {
        MaterialSpecification specification = new MaterialSpecification();
        List<Material> materials = new ArrayList<>();
        materials.add(new Material());
        materials.add(new Material());
        when(materialRepository.findAll(specification.getFilter())).thenReturn(materials);
        List<MaterialDto> result = materialService.getAll(specification);

        assertEquals(0, result.size());
    }

    @Test
    void save_ReturnsSavedMaterial() {
        // Arrange
        Material material = new Material();
        when(materialRepository.save(material)).thenReturn(material);

        // Act
        Material result = materialService.save(material);

        // Assert
        assertEquals(material, result);
    }

    @Test
    void create_WithValidManufacturerId_ReturnsMaterialDto() {
        // Arrange
        MaterialCreateCommand command = new MaterialCreateCommand();
        command.setManufacturerId(1L);
        Manufacturer manufacturer = new Manufacturer();
        when(manufacturerRepository.findById(1L)).thenReturn(Optional.of(manufacturer));
        when(materialRepository.save(any(Material.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        MaterialDto result = materialService.create(command);

        // Assert
        assertNotNull(result);
        assertEquals(manufacturer, result.getManufacturer());
    }

    @Test
    void create_WithInvalidManufacturerId_ThrowsException() {
        // Arrange
        MaterialCreateCommand command = new MaterialCreateCommand();
        command.setManufacturerId(1L);
        when(manufacturerRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> materialService.create(command));
    }

    @Test
    void delete_WithValidId_DeletesMaterial() {
        // Arrange
        Long id = 1L;

        // Act
        materialService.delete(id);

        // Assert
        verify(materialRepository, times(1)).deleteById(id);
    }

    @Test
    void getById_WithInvalidId_ThrowsException() {
        Long id = 1L;
        when(materialRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> materialService.getById(id));
    }

    @Test
    void getMaterialsByIds_ReturnsSetOfMaterials() {
        Set<Long> ids = new HashSet<>(Arrays.asList(1L, 2L));
        List<Material> materials = new ArrayList<>();
        materials.add(new Material());
        materials.add(new Material());
        when(materialRepository.findAllById(ids)).thenReturn(materials);

    }
}
