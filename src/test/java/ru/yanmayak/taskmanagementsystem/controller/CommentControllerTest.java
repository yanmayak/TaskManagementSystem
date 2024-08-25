package ru.yanmayak.taskmanagementsystem.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.yanmayak.taskmanagementsystem.dto.task.CommentDto;
import ru.yanmayak.taskmanagementsystem.service.task.CommentService;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentControllerTest {

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    @Test
    void testCreateComment() {
        CommentDto commentDto = new CommentDto();
        CommentDto createdComment = new CommentDto();

        when(commentService.createComment(commentDto)).thenReturn(createdComment);

        ResponseEntity<CommentDto> response = commentController.createComment(commentDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdComment, response.getBody());
    }

    @Test
    void testGetComment() {
        UUID commentId = UUID.randomUUID();
        CommentDto commentDto = new CommentDto();

        when(commentService.getComment(commentId)).thenReturn(commentDto);

        ResponseEntity<CommentDto> response = commentController.getComment(commentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(commentDto, response.getBody());
    }

    @Test
    void testDeleteComment() {
        UUID commentId = UUID.randomUUID();

        ResponseEntity<Void> response = commentController.deleteComment(commentId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testGetCommentsByTask() {
        UUID taskId = UUID.randomUUID();
        Pageable pageable = PageRequest.of(0, 10);
        List<CommentDto> commentList = List.of(new CommentDto(), new CommentDto());
        Page<CommentDto> commentsPage = new PageImpl<>(commentList, pageable, commentList.size());

        when(commentService.getCommentsByTask(taskId, pageable)).thenReturn(commentsPage);

        ResponseEntity<Page<CommentDto>> response = commentController.getCommentsByTask(taskId, pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(commentsPage, response.getBody());
    }
}
