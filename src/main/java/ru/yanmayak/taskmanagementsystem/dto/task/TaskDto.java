package ru.yanmayak.taskmanagementsystem.dto.task;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.yanmayak.taskmanagementsystem.dto.user.UserDto;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Getter
@Setter
public class TaskDto {
    private UUID id;
    private String title;
    private String description;
    private String priority;
    private String status;
    private UserDto author;
    private UUID authorId;
    private UserDto assignee;
    private UUID assigneeId;
    private LocalDateTime publishDate;
    private LocalDateTime deadline;
}
