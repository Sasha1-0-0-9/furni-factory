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

    //TODO implement endpoints

    @GetMapping("/cart")
    //тут я додам фільтр, щоб сюди попадали всі меблі, які юзер створив.
    //Я уявляю це так, є список меблів, напроти кожного якийсь маленький інпут чи плюсик, щоб змінити кількість,
    //і кнопка для створення замовлення, на яку коли натискаєш, то вона відправляє пост запит на "/{id}/order".
    //якщо є інші варіки, то пиши
    public ResponseEntity<List<FurnitureDto>> getCart() {
        return ResponseEntity.ok(furnitureService.getAll());
    }

    @PostMapping
    public ResponseEntity<FurnitureDto> create(FurnitureCreateCommand command) {
        return ResponseEntity.ok(furnitureService.create(command));
    }

    @PostMapping("/{id}/order")
    public ResponseEntity<OrderDto> order(@PathVariable Long id, OrderCreateCommand command) {
        return ResponseEntity.ok(orderService.create(id, command));
    }
}
