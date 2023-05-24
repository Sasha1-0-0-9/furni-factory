package com.example.furnifactory.order;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAll(OrderSpecification specification) {
        return ResponseEntity.ok(orderService.getPendings(specification));
    }

    @PatchMapping("/{orderId}/manage")
    public ResponseEntity<OrderDto> manage(@PathVariable Long orderId, ChangeOrderStatusCommand command) {
        return ResponseEntity.ok(orderService.manage(orderId, command));
    }

    @PostMapping("/{userId}/order-all")
    public ResponseEntity<List<OrderDto>> orderAll(@PathVariable Long userId, OrderAllCommand command) {
        return ResponseEntity.ok(orderService.orderAllFromCart(userId, command));
    }
}
