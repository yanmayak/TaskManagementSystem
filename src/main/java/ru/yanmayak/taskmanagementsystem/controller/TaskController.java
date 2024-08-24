package ru.yanmayak.taskmanagementsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
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
import org.springframework.web.bind.annotation.RestController;
import ru.yanmayak.taskmanagementsystem.dto.task.TaskDto;
import ru.yanmayak.taskmanagementsystem.dto.task.TaskFilterDto;
import ru.yanmayak.taskmanagementsystem.dto.task.TaskPatchDto;
import ru.yanmayak.taskmanagementsystem.service.task.TaskService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tasks")
@Tag(name = "Задачи")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успешный запрос"),
        @ApiResponse(responseCode = "400", description = "Неверный запрос"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
        @ApiResponse(responseCode = "404", description = "Ресурс не найден"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
})
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    @Operation(summary = "Создание задачи")
            public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        TaskDto createdTask = taskService.createTask(taskDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @PatchMapping("/{taskId}")
    @Operation(summary = "Обновление статуса задачи (доступно только авторам и исполнителям задачи)")
            public ResponseEntity<TaskDto> updateTask(@PathVariable UUID taskId, @ParameterObject TaskPatchDto taskPatchDto) {
        return ResponseEntity.ok(taskService.patchTask(taskId, taskPatchDto));
    }

    @PutMapping("/{taskId}")
    @Operation(summary = "Обновление задачи (доступно только авторам задачи)")
            public ResponseEntity<TaskDto> updateTask(@PathVariable UUID taskId, @RequestBody TaskDto taskDto) {
        return ResponseEntity.ok(taskService.updateTask(taskId, taskDto));
    }

    @DeleteMapping("/{taskId}")
    @Operation(summary = "Удаление задачи (доступно только авторам задачи)")
            public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    @Operation(summary = "Получение списка всех задач")
            private ResponseEntity<List<TaskDto>> getAllTasks() {
        List<TaskDto> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping
    @Operation(summary = "Получение списка задач после фильтрации и пагинации")
            public Page<TaskDto> getTasks(@ParameterObject TaskFilterDto filterDto, @ParameterObject Pageable pageable) {
        return taskService.getTasks(filterDto, pageable);
    }


//
//    @GetMapping("/{id}")
//    private ResponseEntity<TaskDto> getTask(@PathVariable UUID id) {
//        TaskDto taskDto = taskService.getTask(id);
//        return ResponseEntity.ok(taskDto);
//    }
//    @GetMapping("/assignee")
//    public ResponseEntity<Page<TaskDto>> getTasksByAssignee(
//            @RequestParam UUID assigneeId,
//            @RequestParam int page,
//            @RequestParam int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<TaskDto> tasks = taskService.getTasksByAssignee(assigneeId, pageable);
//        return ResponseEntity.ok(tasks);
//    }
//    @GetMapping("/author")
//    public ResponseEntity<Page<TaskDto>> getTasksByAuthor(
//            @RequestParam UUID authorId,
//            @RequestParam int page,
//            @RequestParam int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<TaskDto> tasks = taskService.getTasksByAuthor(authorId, pageable);
//        return ResponseEntity.ok(tasks);
//    }
//
//    @GetMapping("/priority")
//    public ResponseEntity<Page<TaskDto>> getTasksByPriority(
//            @RequestParam String priority,
//            @RequestParam int page,
//            @RequestParam int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<TaskDto> tasks = taskService.getTasksByPriority(priority, pageable);
//        return ResponseEntity.ok(tasks);
//    }
//
//    @GetMapping("/status")
//    public ResponseEntity<Page<TaskDto>> getTasksByStatus(
//            @RequestParam String status,
//            @RequestParam int page,
//            @RequestParam int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<TaskDto> tasks = taskService.getTasksByStatus(status, pageable);
//        return ResponseEntity.ok(tasks);
//    }
//
//    @GetMapping("/title")
//    public ResponseEntity<Page<TaskDto>> getTasksByTitle(
//            @RequestParam String title,
//            @RequestParam int page,
//            @RequestParam int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<TaskDto> tasks = taskService.getTaskByTitle(title, pageable);
//        return ResponseEntity.ok(tasks);
//    }
}
