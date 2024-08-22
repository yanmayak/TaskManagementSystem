package ru.yanmayak.taskmanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import ru.yanmayak.taskmanagementsystem.dto.task.TaskDto;
import ru.yanmayak.taskmanagementsystem.entity.Task;

import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {TaskMapper.class})
public interface TaskMapper {
    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "assigneeId", source = "assignee.id")
    TaskDto toTaskDto(Task task);

    @Mapping(target = "author", source = "authorId")
    @Mapping(target = "assignee", source = "assigneeId")
    Task toTask(TaskDto taskDto);

    void updateTask(TaskDto taskDto, @MappingTarget Task task);

    @Mapping(target = "id", source = "id")
    Task toTask(UUID TaskId);

    default UUID fromTask(Task task) {
        return task != null ? task.getId() : null;
    }
}
