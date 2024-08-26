package ru.yanmayak.taskmanagementsystem.service.comment;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yanmayak.taskmanagementsystem.dto.comment.CommentCreateDto;
import ru.yanmayak.taskmanagementsystem.dto.comment.CommentGetDto;
import ru.yanmayak.taskmanagementsystem.dto.comment.CommentsGetDto;
import ru.yanmayak.taskmanagementsystem.exception.ResourceNotFoundException;
import ru.yanmayak.taskmanagementsystem.exception.UnauthorizedException;
import ru.yanmayak.taskmanagementsystem.repository.CommentRepository;
import ru.yanmayak.taskmanagementsystem.repository.UserRepository;

import java.util.UUID;

@Service("CommentsSecure")
public class CommentSecureService implements Comments{
    private final Comments comments;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentSecureService(
        @Qualifier("Comments") Comments comments,
        CommentRepository commentRepository,
        UserRepository userRepository
    ) {
        this.comments = comments;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public CommentGetDto createComment(UUID taskId, CommentCreateDto commentDto) {
        if(!commentAuthorCheck(SecurityContextHolder.getContext().getAuthentication().getName(), commentDto.getAuthorId())) {
            throw new UnauthorizedException("User not allowed to create comment");
        }
        return comments.createComment(taskId, commentDto);
    }

    @Override
    public CommentGetDto getComment(UUID id) {
        return comments.getComment(id);
    }

    @Transactional
    @Override
    public void deleteComment(UUID id) {
        if(!commentCreatedByUser(SecurityContextHolder.getContext().getAuthentication().getName(), id)) {
            throw new UnauthorizedException("User not allowed to delete comment");
        }
        comments.deleteComment(id);
    }

    @Override
    public CommentsGetDto getCommentsByTask(UUID taskId, Integer page, Integer size) {
        return comments.getCommentsByTask(taskId, page, size);
    }

    private boolean commentAuthorCheck(String username, UUID userId) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"))
                .getId()
                .equals(userId);
    }

    private boolean commentCreatedByUser(String username, UUID commentId) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"))
                .getId()
                .equals(
                        commentRepository.findById(commentId)
                                .orElseThrow(() -> new ResourceNotFoundException("Author not found"))
                                .getAuthor()
                                .getId()
                );
    }
}
