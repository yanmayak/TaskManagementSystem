package ru.yanmayak.taskmanagementsystem.service.comment;

import ru.yanmayak.taskmanagementsystem.dto.comment.CommentCreateDto;
import ru.yanmayak.taskmanagementsystem.dto.comment.CommentGetDto;
import ru.yanmayak.taskmanagementsystem.dto.comment.CommentsGetDto;

import java.util.UUID;

public interface Comments {
    CommentGetDto createComment(UUID taskId, CommentCreateDto commentDto);
    CommentGetDto getComment(UUID id);
    void deleteComment(UUID id);
    CommentsGetDto getCommentsByTask(UUID taskId, Integer page, Integer size);
}
