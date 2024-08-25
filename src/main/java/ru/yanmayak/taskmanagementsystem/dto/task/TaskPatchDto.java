package ru.yanmayak.taskmanagementsystem.dto.task;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Getter
@Setter
public class TaskPatchDto {
    private String status;
}
