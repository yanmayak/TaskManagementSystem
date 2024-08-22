package ru.yanmayak.taskmanagementsystem.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private UUID id;
    private UUID authorId;
    private UUID taskId;
    private String content;
    private LocalDateTime publishDate;
}
