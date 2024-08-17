package ru.yanmayak.taskmanagementsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yanmayak.taskmanagementsystem.service.UserService;

@RestController
@RequestMapping("/example")
@RequiredArgsConstructor
@Tag(name = "Аутетнтификация")
public class ExampleController {
    private final UserService userService;

    @GetMapping("/api/private")
    @Operation(summary = "Доступно только для авторизованных пользователей")
    public String message() {
        return "hello, user";
    }

    @GetMapping("/api/public")
    @Operation(summary = "Доступно для всех")
    public String message2() {
        return "hello";
    }

    @GetMapping("/get-admin")
    @Operation(summary = "Получить роль ADMIN (для демонстрации)")
    public void getAdmin() {
        userService.getAdmin();
    }
}
