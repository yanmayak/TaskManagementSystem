package ru.yanmayak.taskmanagementsystem.service.task;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.yanmayak.taskmanagementsystem.dto.task.TaskDto;
import ru.yanmayak.taskmanagementsystem.entity.Task;
import ru.yanmayak.taskmanagementsystem.mapper.TaskMapper;
import ru.yanmayak.taskmanagementsystem.repository.TaskRepository;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskDto createTask(TaskDto taskDto) {
        Task task = taskMapper.toTask(taskDto);
        task = taskRepository.save(task);
        return taskMapper.toTaskDto(task);
    }

    public TaskDto getTask(UUID id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task Not Found"));
        return taskMapper.toTaskDto(task);
    }

    public Page<TaskDto> getTaskByTitle(String title, Pageable pageable) {
        return taskRepository.findByTitleContaining(title, pageable)
                .map(taskMapper::toTaskDto);
    }

    public Page<TaskDto> getTasksByAssignee(UUID assigneeId, Pageable pageable) {
        return taskRepository.findByAssignee(assigneeId, pageable)
                .map(taskMapper::toTaskDto);
    }

    public Page<TaskDto> getTasksByAuthor(UUID authorId, Pageable pageable) {
        return taskRepository.findByAuthor(authorId, pageable)
                .map(taskMapper::toTaskDto);
    }

    public Page<TaskDto> getTasksByPriority(String priority, Pageable pageable) {
        return taskRepository.findByPriority(priority, pageable)
                .map(taskMapper::toTaskDto);
    }

    public Page<TaskDto> getTasksByStatus(String status, Pageable pageable) {
        return taskRepository.findByStatus(status, pageable)
                .map(taskMapper::toTaskDto);
    }

    public TaskDto updateTask(UUID id, TaskDto taskDto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task Not Found"));
        taskMapper.updateTask(taskDto, task);
        task = taskRepository.save(task);
        return taskMapper.toTaskDto(task);
    }

    public void deleteTask(UUID id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException ("Task Not Found"));
        taskRepository.delete(task);
    }

    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(taskMapper::toTaskDto)
                .collect(Collectors.toList());
    }
}
