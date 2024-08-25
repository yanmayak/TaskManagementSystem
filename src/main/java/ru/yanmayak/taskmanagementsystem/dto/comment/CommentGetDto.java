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
@Schema(description = "Получение карточки комментария")
public class CommentGetDto {
    private UUID id;
    private UUID authorId;
    private String content;
    private LocalDateTime publishDate;
}
