package ru.yanmayak.taskmanagementsystem.dto.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private String title;
    private String description;
    private String priority;
    private String status;
    private UUID authorId;
    private UUID assigneeId;
    private LocalDateTime publishDate;
    private LocalDateTime deadline;
}
