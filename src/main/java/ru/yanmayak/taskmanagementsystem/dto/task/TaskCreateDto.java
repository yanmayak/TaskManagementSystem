package ru.yanmayak.taskmanagementsystem.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.yanmayak.taskmanagementsystem.entity.TaskPriority;
import ru.yanmayak.taskmanagementsystem.entity.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(description = "Dto создания задачи")
public class TaskCreateDto {
    @Schema(description = "Идентификатор автора задачи")
    @NotBlank
    private UUID authorId;

    @Schema(description = "Идентификатор исполнителя задачи")
    @NotBlank
    private UUID assigneeId;

    @Schema(description = "Заголовок задачи")
    @NotBlank
    private String title;

    @Schema(description = "Описание задачи")
    private String description;

    @Schema(description = "Приоритет задачи")
    @NotBlank
    private TaskPriority priority;

    @Schema(description = "Статус задачи")
    @NotBlank
    private TaskStatus status;

    @Schema(description = "Дата создания задачи")
    private LocalDateTime createdDate;
}
