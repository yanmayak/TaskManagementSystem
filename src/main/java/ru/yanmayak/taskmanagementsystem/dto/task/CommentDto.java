package ru.yanmayak.taskmanagementsystem.dto.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.yanmayak.taskmanagementsystem.dto.user.UserDto;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private UUID id;
    private UserDto author;
    private UserDto task;
    private UUID taskId;
    private UUID authorId;
    private String content;
    private LocalDateTime publishDate;
}
