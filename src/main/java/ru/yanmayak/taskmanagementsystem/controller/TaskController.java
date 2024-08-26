package ru.yanmayak.taskmanagementsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yanmayak.taskmanagementsystem.dto.task.TaskCreateDto;
import ru.yanmayak.taskmanagementsystem.dto.task.TaskFilterDto;
import ru.yanmayak.taskmanagementsystem.dto.task.TaskGetDto;
import ru.yanmayak.taskmanagementsystem.dto.task.TaskUpdateDto;
import ru.yanmayak.taskmanagementsystem.dto.task.TasksGetDto;
import ru.yanmayak.taskmanagementsystem.entity.TaskStatus;
import ru.yanmayak.taskmanagementsystem.service.task.TaskService;
import ru.yanmayak.taskmanagementsystem.service.task.Tasks;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tasks")
@Tag(name = "Задачи")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Успешный запрос"),
    @ApiResponse(responseCode = "400", description = "Неверный запрос"),
    @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
    @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
    @ApiResponse(responseCode = "404", description = "Ресурс не найден"),
    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
})
public class TaskController {
    private final Tasks taskService;

    public TaskController(@Qualifier("TasksSecure") Tasks taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @Operation(summary = "Создание задачи")
    public ResponseEntity<TaskGetDto> createTask(@Valid @RequestBody TaskCreateDto task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @PatchMapping("/{task-id}")
    @Operation(summary = "Обновление статуса задачи (доступно только авторам и исполнителям задачи)")
    public ResponseEntity<TaskGetDto> updateTask(@PathVariable("task-id") UUID taskId, @Valid @RequestParam TaskStatus status) {
        return ResponseEntity.ok(taskService.changeStatus(taskId, status));
    }

    @PutMapping("/{task-id}")
    @Operation(summary = "Обновление задачи (доступно только авторам задачи)")
    public ResponseEntity<TaskGetDto> updateTask(@PathVariable("task-id") UUID taskId, @Valid @RequestBody TaskUpdateDto taskDto) {
        return ResponseEntity.ok(taskService.updateTask(taskId, taskDto));
    }

    @DeleteMapping("/{task-id}")
    @Operation(summary = "Удаление задачи (доступно только авторам задачи)")
    public void deleteTask(@PathVariable("task-id") UUID id) {
        taskService.deleteTask(id);
    }

    @GetMapping("/{task-id}")
    @Operation(summary = "Получение списка задач")
    public ResponseEntity<TaskGetDto> getTask(@RequestParam("task-id") UUID id) {
        return ResponseEntity.ok(taskService.getTask(id));
    }

    @GetMapping
    @Operation(summary = "Получение списка задач после фильтрации и пагинации")
    public ResponseEntity<TasksGetDto> getTasks(@Valid @ParameterObject TaskFilterDto filterDto) {
        return ResponseEntity.ok(taskService.getTasks(filterDto));
    }
}
