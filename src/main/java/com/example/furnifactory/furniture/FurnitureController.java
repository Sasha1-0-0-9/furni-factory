package com.example.furnifactory.furniture;

import com.example.furnifactory.order.OrderCreateCommand;
import com.example.furnifactory.order.OrderDto;
import com.example.furnifactory.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/furniture")
@RequiredArgsConstructor
public class FurnitureController {
    private final FurnitureService furnitureService;

    private final OrderService orderService;

    @GetMapping("/cart")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<FurnitureDto>> getCart() {
        return ResponseEntity.ok(furnitureService.getAll());
    }

    @PostMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<FurnitureDto> create(FurnitureCreateCommand command) {
        return ResponseEntity.ok(furnitureService.create(command));
    }

    @PostMapping("/{id}/order")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<OrderDto> order(@PathVariable Long id, OrderCreateCommand command) {
        return ResponseEntity.ok(orderService.create(id, command));
    }
}
