package ru.yanmayak.taskmanagementsystem.service.task;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yanmayak.taskmanagementsystem.dto.task.TaskCreateDto;
import ru.yanmayak.taskmanagementsystem.dto.task.TaskFilterDto;
import ru.yanmayak.taskmanagementsystem.dto.task.TaskGetDto;
import ru.yanmayak.taskmanagementsystem.dto.task.TaskUpdateDto;
import ru.yanmayak.taskmanagementsystem.dto.task.TasksGetDto;
import ru.yanmayak.taskmanagementsystem.entity.Task;
import ru.yanmayak.taskmanagementsystem.entity.TaskStatus;
import ru.yanmayak.taskmanagementsystem.exception.ResourceNotFoundException;
import ru.yanmayak.taskmanagementsystem.mapper.task.TaskMapper;
import ru.yanmayak.taskmanagementsystem.mapper.task.TaskSpecificationMapper;
import ru.yanmayak.taskmanagementsystem.mapper.task.TaskUpdate;
import ru.yanmayak.taskmanagementsystem.repository.TaskRepository;

import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@Service("Tasks")
@AllArgsConstructor
public class TaskService implements Tasks {
    private final TaskRepository taskRepository;
    private final TaskUpdate taskUpdate;
    private final TaskMapper taskMapper;
    private final TaskSpecificationMapper taskSpecificationMapper;


    @Override
    public TaskGetDto createTask(TaskCreateDto taskDto) {
        return taskMapper.mapToDto(
                taskRepository.save(
                        taskMapper.mapToDomain(
                                taskDto
                        )
                )
        );
    }

    @Override
    public TaskGetDto getTask(UUID taskId) {
        return taskMapper.mapToDto(
                taskRepository.findById(taskId)
                        .orElseThrow(() -> new ResourceNotFoundException("Task not found"))
        );
    }

    @Override
    public TaskGetDto updateTask(UUID id, TaskUpdateDto taskDto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        taskUpdate.updateTaskFromDto(task, taskDto);
        return taskMapper.mapToDto(
                taskRepository.save(task)
        );
    }

    @Override
    public TaskGetDto changeStatus(UUID id, TaskStatus status) {
        return taskMapper.mapToDto(
                taskRepository.findById(id)
                        .map(task -> {
                            task.setStatus(status);
                            return taskRepository.save(task);
                        }).orElseThrow(() -> new ResourceNotFoundException("Task not found"))
        );
    }

    @Override
    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }

    @Override
    public TasksGetDto getTasks(TaskFilterDto filterDto) {
        Page<Task> tasks = taskRepository.findAll(
                taskSpecificationMapper.toSpecification(filterDto),
                PageRequest.of(filterDto.getPage(), filterDto.getSize())
        );
        return new TasksGetDto(
                tasks.get().map(taskMapper::mapToDto).collect(Collectors.toList()),
                filterDto.getPage(),
                filterDto.getSize(),
                tasks.getTotalElements()
        );
    }
}
