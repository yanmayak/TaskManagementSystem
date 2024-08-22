package ru.yanmayak.taskmanagementsystem.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yanmayak.taskmanagementsystem.dto.task.TaskDto;
import ru.yanmayak.taskmanagementsystem.service.task.TaskService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tasks")
@Tag(name = "Задачи")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        TaskDto createdTask = taskService.createTask(taskDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @GetMapping("/{id}")
    private ResponseEntity<TaskDto> getTask(@PathVariable UUID id) {
        TaskDto taskDto = taskService.getTask(id);
        return ResponseEntity.ok(taskDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable UUID id, @RequestBody TaskDto taskDto) {
        TaskDto updatedTask = taskService.updateTask(id, taskDto);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    private ResponseEntity<List<TaskDto>> getAllTasks() {
        List<TaskDto> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/assignee")
    public ResponseEntity<Page<TaskDto>> getTasksByAssignee(
            @RequestParam UUID assigneeId,
            @RequestParam int page,
            @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TaskDto> tasks = taskService.getTasksByAssignee(assigneeId, pageable);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/author")
    public ResponseEntity<Page<TaskDto>> getTasksByAuthor(
            @RequestParam UUID authorId,
            @RequestParam int page,
            @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TaskDto> tasks = taskService.getTasksByAuthor(authorId, pageable);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/priority")
    public ResponseEntity<Page<TaskDto>> getTasksByPriority(
            @RequestParam String priority,
            @RequestParam int page,
            @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TaskDto> tasks = taskService.getTasksByPriority(priority, pageable);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/status")
    public ResponseEntity<Page<TaskDto>> getTasksByStatus(
            @RequestParam String status,
            @RequestParam int page,
            @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TaskDto> tasks = taskService.getTasksByStatus(status, pageable);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/title")
    public ResponseEntity<Page<TaskDto>> getTasksByTitle(
            @RequestParam String title,
            @RequestParam int page,
            @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TaskDto> tasks = taskService.getTaskByTitle(title, pageable);
        return ResponseEntity.ok(tasks);
    }
}
