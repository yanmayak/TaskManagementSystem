package ru.yanmayak.taskmanagementsystem.service.task;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.yanmayak.taskmanagementsystem.dto.task.TaskDto;
import ru.yanmayak.taskmanagementsystem.dto.task.TaskFilterDto;
import ru.yanmayak.taskmanagementsystem.dto.task.TaskPatchDto;
import ru.yanmayak.taskmanagementsystem.entity.Task;
import ru.yanmayak.taskmanagementsystem.entity.User;
import ru.yanmayak.taskmanagementsystem.exeption.ResourceNotFoundException;
import ru.yanmayak.taskmanagementsystem.exeption.UnauthorizedException;
import ru.yanmayak.taskmanagementsystem.mapper.TaskMapper;
import ru.yanmayak.taskmanagementsystem.mapper.TaskSpecificationMapper;
import ru.yanmayak.taskmanagementsystem.repository.TaskRepository;
import org.springframework.data.domain.Pageable;
import ru.yanmayak.taskmanagementsystem.repository.UserRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;
    private final TaskSpecificationMapper taskSpecificationMapper;

    public TaskDto createTask(TaskDto taskDto) {
        Task task = taskMapper.toTask(taskDto);
        task = taskRepository.save(task);
        return taskMapper.toTaskDto(task);
    }

    public TaskDto updateTask(UUID id, TaskDto taskDto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task Not Found"));
        UUID currentUserId = getCurrentUserId();
        boolean isAuthor = task.getAuthor().getId().equals(currentUserId);

        if (isAuthor) {
            task.setTitle(taskDto.getTitle());
            task.setDescription(taskDto.getDescription());
            task.setAuthor(task.getAuthor());
            task.setPriority(taskDto.getPriority());
            task.setStatus(taskDto.getStatus());
            task.setAssignee(taskMapper.toTask(taskDto).getAssignee());
            task.setDeadline(taskDto.getDeadline());
        } else throw new UnauthorizedException("You are not authorized to edit this task");

        task = taskRepository.save(task);
        return taskMapper.toTaskDto(task);
    }

    public TaskDto patchTask(UUID id, TaskPatchDto taskPatchDto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task Not Found"));

        UUID currentUserId = getCurrentUserId();
        boolean isAuthor = task.getAuthor().getId().equals(currentUserId);
        boolean isAssignee = task.getAssignee().getId().equals(currentUserId);

        if (isAssignee || isAuthor) {
            if (taskPatchDto.getStatus() != null) {
                task.setStatus(taskPatchDto.getStatus());
            }
        } else throw new UnauthorizedException("You are not authorized to edit this task");

        task = taskRepository.save(task);
        return taskMapper.toTaskDto(task);
    }

    public void deleteTask(UUID id) {
        UUID currentUserId = getCurrentUserId();
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task Not Found"));
        boolean isAuthor = task.getAuthor().getId().equals(currentUserId);
        if (isAuthor) {
            taskRepository.delete(task);
        } else throw new UnauthorizedException("You are not authorized to delete this task");
    }

    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(taskMapper::toTaskDto)
                .collect(Collectors.toList());
    }

    public Page<TaskDto> getTasks(TaskFilterDto filterDto, Pageable pageable) {
        Specification<Task> spec = taskSpecificationMapper.toSpecification(filterDto);
        return taskRepository.findAll(spec, pageable)
                .map(taskMapper::toTaskDto);
    }

    private UUID getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return UUID.fromString(authentication.getName());
    }

//    public TaskDto getTask(UUID id) {
//        Task task = taskRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Task Not Found"));
//        return taskMapper.toTaskDto(task);
//    }
//
//    public Page<TaskDto> getTaskByTitle(String title, Pageable pageable) {
//        return taskRepository.findByTitleContaining(title, pageable)
//                .map(taskMapper::toTaskDto);
//    }
//
//    public Page<TaskDto> getTasksByAssignee(UUID assigneeId, Pageable pageable) {
//        return taskRepository.findByAssignee(assigneeId, pageable)
//                .map(taskMapper::toTaskDto);
//    }
//
//    public Page<TaskDto> getTasksByAuthor(UUID authorId, Pageable pageable) {
//        return taskRepository.findByAuthor(authorId, pageable)
//                .map(taskMapper::toTaskDto);
//    }
//
//    public Page<TaskDto> getTasksByPriority(String priority, Pageable pageable) {
//        return taskRepository.findByPriority(priority, pageable)
//                .map(taskMapper::toTaskDto);
//    }
//
//    public Page<TaskDto> getTasksByStatus(String status, Pageable pageable) {
//        return taskRepository.findByStatus(status, pageable)
//                .map(taskMapper::toTaskDto);
//    }




}
