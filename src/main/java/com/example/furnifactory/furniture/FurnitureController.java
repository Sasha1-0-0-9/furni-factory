package com.example.furnifactory.furniture;

import com.example.furnifactory.order.OrderCreateCommand;
import com.example.furnifactory.order.OrderDto;
import com.example.furnifactory.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/v1/furniture")
@RequiredArgsConstructor
public class FurnitureController {
    private final FurnitureService furnitureService;

    private final OrderService orderService;

    @GetMapping("/{userId}/cart")
    //@CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<FurnitureDto>> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(furnitureService.getCart(userId));
    }

    @PostMapping
    //@CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<FurnitureDto> create(FurnitureCreateCommand command) {
        return ResponseEntity.ok(furnitureService.create(command));
    }

    @PostMapping("/{furnitureId}/order")
   // @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<OrderDto> order(@PathVariable Long furnitureId, OrderCreateCommand command) {
        return ResponseEntity.ok(orderService.create(furnitureId, command));
    }

    @GetMapping("/price")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Double> getFurniturePrice(FurnitureCreateCommand command) {
        return ResponseEntity.ok(furnitureService.getFurniturePrice(command));
    }

}
