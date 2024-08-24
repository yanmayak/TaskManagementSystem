package ru.yanmayak.taskmanagementsystem.dto.task;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Data
@Getter
@Setter
public class TaskFilterDto {
    private String title;
    private UUID assigneeId;
    private UUID authorId;
    private String priority;
    private String status;
}
