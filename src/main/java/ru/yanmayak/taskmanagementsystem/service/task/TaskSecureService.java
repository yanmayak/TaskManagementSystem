package ru.yanmayak.taskmanagementsystem.service.task;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.yanmayak.taskmanagementsystem.dto.task.TaskCreateDto;
import ru.yanmayak.taskmanagementsystem.dto.task.TaskFilterDto;
import ru.yanmayak.taskmanagementsystem.dto.task.TaskGetDto;
import ru.yanmayak.taskmanagementsystem.dto.task.TaskUpdateDto;
import ru.yanmayak.taskmanagementsystem.dto.task.TasksGetDto;
import ru.yanmayak.taskmanagementsystem.entity.Task;
import ru.yanmayak.taskmanagementsystem.entity.TaskStatus;
import ru.yanmayak.taskmanagementsystem.exception.ResourceNotFoundException;
import ru.yanmayak.taskmanagementsystem.exception.UnauthorizedException;
import ru.yanmayak.taskmanagementsystem.repository.TaskRepository;
import ru.yanmayak.taskmanagementsystem.repository.UserRepository;

import java.util.UUID;

@Service("TasksSecure")
public class TaskSecureService implements Tasks {
    private final Tasks tasks;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public TaskSecureService(
        @Qualifier("Tasks") Tasks tasks,
        UserRepository userRepository,
        TaskRepository taskRepository
    ) {
        this.tasks = tasks;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }


    @Override
    public TaskGetDto createTask(TaskCreateDto taskDto) {
        if(!taskCreatedByUser(SecurityContextHolder.getContext().getAuthentication().getName(), taskDto.getAuthorId())) {
            throw new UnauthorizedException("User not allowed to create a new task");
        }
        return tasks.createTask(taskDto);
    }

    @Override
    public TaskGetDto getTask(UUID taskId) {
        return tasks.getTask(taskId);
    }

    @Override
    public TaskGetDto updateTask(UUID id, TaskUpdateDto taskDto) {
        boolean isTaskAuthor = taskCreatedByUser(
                SecurityContextHolder.getContext().getAuthentication().getName(),
                taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task Not Found")).getAuthor().getId()
        );
        if(!isTaskAuthor) {
            throw new UnauthorizedException("User not allowed to update other user's task");
        }
        return tasks.updateTask(id, taskDto);
    }

    @Override
    public TaskGetDto changeStatus(UUID id, TaskStatus status) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task Not Found"));
        boolean isTaskAuthor = taskCreatedByUser(username, task.getAuthor().getId());
        boolean isTaskAssignee = taskCreatedByUser(username, task.getAssignee().getId());
        if(!isTaskAuthor && !isTaskAssignee) {
            throw new UnauthorizedException("Only task author and task assignee can change task status");
        }
        return tasks.changeStatus(id, status);
    }

    @Override
    public void deleteTask(UUID id) {
        boolean isTaskAuthor = taskCreatedByUser(
                SecurityContextHolder.getContext().getAuthentication().getName(),
                taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task Not Found")).getAuthor().getId()
        );
        if(!isTaskAuthor) {
            throw new UnauthorizedException("User not allowed to delete other user's task");
        }
        tasks.deleteTask(id);
    }

    @Override
    public TasksGetDto getTasks(TaskFilterDto filterDto) {
        return tasks.getTasks(filterDto);
    }

    private boolean taskCreatedByUser(String username, UUID userId) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"))
                .getId()
                .equals(userId);
    }
}
