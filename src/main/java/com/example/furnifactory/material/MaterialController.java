package com.example.furnifactory.material;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/materials")
public class MaterialController {
    private final MaterialService materialService;

    @GetMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<MaterialDto>> getAll(MaterialSpecification specification) {
        return ResponseEntity.ok(materialService.getAll(specification));
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<MaterialDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(materialService.getById(id));
    }

    @PostMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<MaterialDto> createMaterial(@RequestBody MaterialCreateCommand command) {
        return ResponseEntity.ok(materialService.create(command));
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Void> deleteMaterial(@PathVariable Long id) {
        materialService.delete(id);
        return ResponseEntity.ok().build();
    }

    // todo update
}
