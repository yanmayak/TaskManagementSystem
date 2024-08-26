package ru.yanmayak.taskmanagementsystem.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Создание комментария")
public class CommentCreateDto {
    private UUID authorId;
    private String content;
    private LocalDateTime publishDate;
}
