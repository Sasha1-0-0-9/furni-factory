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
    //сторінка зі списком матеріалів
    public ResponseEntity<List<MaterialDto>> getAll() {
        return ResponseEntity.ok(materialService.getAll());
    }

    @GetMapping("/{id}")
    //профіль матеріалу, чисто зайти почитать дескріпшн і тд
    public ResponseEntity<MaterialDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(materialService.getById(id));
    }

    @PostMapping
    public ResponseEntity<MaterialDto> createMaterial(@RequestBody MaterialCreateCommand command) {
        return ResponseEntity.ok(materialService.create(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable Long id) {
        materialService.delete(id);
        return ResponseEntity.ok().build();
    }

    // todo update
}
