package ru.yanmayak.taskmanagementsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yanmayak.taskmanagementsystem.dto.task.CommentDto;
import ru.yanmayak.taskmanagementsystem.service.task.CommentService;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tasks/{taskId}/comments")
@RequiredArgsConstructor
@Tag(name = "Комментарии")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успешный запрос"),
        @ApiResponse(responseCode = "400", description = "Неверный запрос"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
        @ApiResponse(responseCode = "404", description = "Ресурс не найден"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
})
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    @Operation(summary = "Создание комментария")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDTO) {
        CommentDto createdComment = commentService.createComment(commentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение комментария")
            public ResponseEntity<CommentDto> getComment(@PathVariable UUID id) {
        CommentDto commentDto = commentService.getComment(id);
        return ResponseEntity.ok(commentDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление комментария")
            public ResponseEntity<Void> deleteComment(@PathVariable UUID id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Получение всех комментариев к задаче")
            public ResponseEntity<Page<CommentDto>> getCommentsByTask(@PathVariable UUID taskId, Pageable pageable) {
        Page<CommentDto> comments = commentService.getCommentsByTask(taskId, pageable);
        return ResponseEntity.ok(comments);
    }

}
