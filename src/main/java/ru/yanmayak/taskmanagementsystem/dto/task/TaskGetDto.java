package ru.yanmayak.taskmanagementsystem.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.yanmayak.taskmanagementsystem.dto.user.UserDto;
import ru.yanmayak.taskmanagementsystem.entity.TaskPriority;
import ru.yanmayak.taskmanagementsystem.entity.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Получение карточки задачи")
public class TaskGetDto {
    @Schema(description = "Идентификатор задачи")
    @NotBlank
    private UUID id;

    @Schema(description = "Автор задачи")
    @NotBlank
    private UserDto author;

    @Schema(description = "Исполнитель задачи")
    private UserDto assignee;

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
    @NotBlank
    private LocalDateTime createdDate;

    @Schema(description = "Дата закрытия задачи")
    private LocalDateTime closedDate;
}
