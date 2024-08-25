package ru.yanmayak.taskmanagementsystem.service.task;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yanmayak.taskmanagementsystem.dto.task.CommentDto;
import ru.yanmayak.taskmanagementsystem.entity.Comment;
import ru.yanmayak.taskmanagementsystem.entity.User;
import ru.yanmayak.taskmanagementsystem.exeption.ResourceNotFoundException;
import ru.yanmayak.taskmanagementsystem.exeption.UnauthorizedException;
import ru.yanmayak.taskmanagementsystem.mapper.CommentMapper;
import ru.yanmayak.taskmanagementsystem.repository.CommentRepository;
import ru.yanmayak.taskmanagementsystem.repository.TaskRepository;
import ru.yanmayak.taskmanagementsystem.repository.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public CommentDto createComment(CommentDto commentDto) {
        Comment comment = commentMapper.toComment(commentDto);

        comment.setTask(taskRepository.findById(commentDto.getTaskId())
                .orElseThrow(() -> new ResourceNotFoundException("Task not found")));
        comment.setAuthor(userRepository.findById(commentDto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found")));

        comment = commentRepository.save(comment);
        return commentMapper.toCommentDto(comment);
    }

    public CommentDto getComment(UUID id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        return commentMapper.toCommentDto(comment);
    }


    public void deleteComment(UUID id) {
        UUID currentUserId = getCurrentUserId();
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

        boolean isAuthor = comment.getAuthor().getId().equals(currentUserId);
        if (isAuthor) {
            commentRepository.delete(comment);
        } else throw new UnauthorizedException("You are not authorized to delete this comment");
    }

    public Page<CommentDto> getCommentsByTask(UUID taskId, Pageable pageable) {
        return commentRepository.findAllByTaskId(taskId, pageable)
                .map(commentMapper::toCommentDto);
    }

    private UUID getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return UUID.fromString(authentication.getName());
    }

    private User findUserById(UUID assigneeId) {
        return userRepository.findById(assigneeId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

}
