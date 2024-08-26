package ru.yanmayak.taskmanagementsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yanmayak.taskmanagementsystem.dto.comment.CommentCreateDto;
import ru.yanmayak.taskmanagementsystem.dto.comment.CommentGetDto;
import ru.yanmayak.taskmanagementsystem.dto.comment.CommentsGetDto;
import ru.yanmayak.taskmanagementsystem.service.comment.Comments;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tasks/{task-id}/comments")
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
    private final Comments commentService;

    public CommentController(@Qualifier("CommentsSecure") Comments commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    @Operation(summary = "Создание комментария")
    public ResponseEntity<CommentGetDto> createComment(@PathVariable("task-id") UUID taskId,
                                                       @Valid @RequestBody CommentCreateDto commentCreateDto) {
        return ResponseEntity.ok(commentService.createComment(taskId, commentCreateDto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение комментария")
    public ResponseEntity<CommentGetDto> getComment(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(commentService.getComment(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление комментария")
    public void deleteComment(@PathVariable("id") UUID id) {
        commentService.deleteComment(id);
    }

    @GetMapping
    @Operation(summary = "Получение всех комментариев к задаче")
    public ResponseEntity<CommentsGetDto> getCommentsByTask(
            @RequestParam("task-id") UUID taskId,
            @Min(0) @RequestParam("page") Integer page,
            @Size(min = 1, max = 100) Integer size
    ) {
        return ResponseEntity.ok(commentService.getCommentsByTask(taskId, page, size));
    }
}
