package ru.yanmayak.taskmanagementsystem.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yanmayak.taskmanagementsystem.dto.comment.CommentCreateDto;
import ru.yanmayak.taskmanagementsystem.dto.comment.CommentGetDto;
import ru.yanmayak.taskmanagementsystem.dto.comment.CommentsGetDto;
import ru.yanmayak.taskmanagementsystem.entity.Comment;
import ru.yanmayak.taskmanagementsystem.exception.ResourceNotFoundException;
import ru.yanmayak.taskmanagementsystem.mapper.comment.CommentMapper;
import ru.yanmayak.taskmanagementsystem.repository.CommentRepository;
import ru.yanmayak.taskmanagementsystem.repository.TaskRepository;
import ru.yanmayak.taskmanagementsystem.repository.UserRepository;

import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@Service("Comments")
@RequiredArgsConstructor
public class CommentService implements Comments{
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;


    @Override
    public CommentGetDto createComment(UUID taskId, CommentCreateDto commentDto) {
        return commentMapper.toDto(
                commentRepository.save(
                        new Comment(
                                UUID.randomUUID(),
                                taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task not found")),
                                userRepository.findById(commentDto.getAuthorId()).orElseThrow(() -> new ResourceNotFoundException("User not found")),
                                commentDto.getContent(),
                                commentDto.getPublishDate()
                        )
                )
        );
    }

    @Override
    public CommentGetDto getComment(UUID id) {
        return commentMapper.toDto(
                commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment not found"))
        );
    }

    @Override
    public void deleteComment(UUID id) {
        commentRepository.delete(
                commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment not found"))
        );
    }

    @Override
    public CommentsGetDto getCommentsByTask(UUID taskId, Integer page, Integer size) {
        Page<Comment> comments = commentRepository.findAllByTaskIdOrderByPublishDateDesc(
                taskId,
                PageRequest.of(page, size)
        );
        return new CommentsGetDto(
                comments.get().map(commentMapper::toDto).collect(Collectors.toList()),
                page,
                size,
                comments.getTotalElements()
        );
    }
}
