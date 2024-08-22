package ru.yanmayak.taskmanagementsystem.service.task;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yanmayak.taskmanagementsystem.dto.task.CommentDto;
import ru.yanmayak.taskmanagementsystem.entity.Comment;
import ru.yanmayak.taskmanagementsystem.entity.Task;
import ru.yanmayak.taskmanagementsystem.entity.User;
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


}
