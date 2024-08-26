package ru.yanmayak.taskmanagementsystem.service.task;

import ru.yanmayak.taskmanagementsystem.dto.task.TaskCreateDto;
import ru.yanmayak.taskmanagementsystem.dto.task.TaskFilterDto;
import ru.yanmayak.taskmanagementsystem.dto.task.TaskGetDto;
import ru.yanmayak.taskmanagementsystem.dto.task.TaskUpdateDto;
import ru.yanmayak.taskmanagementsystem.dto.task.TasksGetDto;
import ru.yanmayak.taskmanagementsystem.entity.TaskStatus;

import java.util.UUID;

public interface Tasks {
    TaskGetDto createTask(TaskCreateDto taskDto);
    TaskGetDto getTask(UUID taskId);
    TaskGetDto updateTask(UUID id, TaskUpdateDto taskDto);
    TaskGetDto changeStatus(UUID id, TaskStatus status);
    void deleteTask(UUID id);
    TasksGetDto getTasks(TaskFilterDto filterDto);
}
