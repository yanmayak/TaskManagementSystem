package ru.yanmayak.taskmanagementsystem.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.yanmayak.taskmanagementsystem.entity.TaskPriority;
import ru.yanmayak.taskmanagementsystem.entity.TaskStatus;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(description = "Dto обновления задачи")
public class TaskUpdateDto {
    @Schema(description = "Идентификатор исполнителя задачи")
    private UUID assigneeId;

    @Schema(description = "Заголовок задачи")
    @NotBlank
    @NotNull
    private String title;

    @Schema(description = "Описание задачи")
    private String description;

    @Schema(description = "Приоритет задачи")
    @NotNull
    private TaskPriority priority;

    @Schema(description = "Статус задачи")
    @NotNull
    private TaskStatus status;
}
