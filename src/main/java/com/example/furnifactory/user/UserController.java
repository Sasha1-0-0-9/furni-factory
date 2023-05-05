package com.example.furnifactory.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    //якусь загальну сторінку зі списком юзерів можна вліпить, фільтри додам
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    //профіль, сильно не заморачуйся, я думаю що там буде просто інфа про юзера і список його замовлень.
    //самий дефолтний профіль підійде
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PostMapping("/create")
    //реєстрація
    public ResponseEntity<UserDto> create(UserCreateCommand command) {
        return ResponseEntity.ok(userService.create(command));
    }

    @GetMapping("/login")
    public ResponseEntity<String> auth(UserAuthCommand command) {
        return ResponseEntity.ok(userService.auth(command));
    }

    @DeleteMapping("/{id}")
    //кнопочку можна додать десь в профілі
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
