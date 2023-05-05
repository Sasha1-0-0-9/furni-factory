package com.example.furnifactory.manufacturer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/manufacturers")
@RequiredArgsConstructor
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    @GetMapping
    public ResponseEntity<List<ManufacturerDto>> getAll() {
        return ResponseEntity.ok(manufacturerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManufacturerDto> getById(Long id) {
        return ResponseEntity.ok(manufacturerService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ManufacturerDto> create(ManufacturerCreateCommand command) {
        return ResponseEntity.ok(manufacturerService.createManufacturer(command));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(Long id) {
        manufacturerService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<ManufacturerDto> update(Long id, ManufacturerCreateCommand command) {
        return ResponseEntity.ok(manufacturerService.updateManufacturer(id, command));
    }
}
