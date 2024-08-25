package ru.yanmayak.taskmanagementsystem.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Список комментариев")
public class CommentsGetDto {
    private List<CommentGetDto> comments;
    private Integer page;
    private Integer size;
    private Long total;
}
