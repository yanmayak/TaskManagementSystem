package ru.yanmayak.taskmanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.yanmayak.taskmanagementsystem.dto.task.TaskDto;
import ru.yanmayak.taskmanagementsystem.dto.task.TaskPatchDto;
import ru.yanmayak.taskmanagementsystem.entity.Task;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TaskMapper {
    @Mapping(target = "author", source = "author")
    @Mapping(target = "assignee", source = "assignee")
    TaskDto toTaskDto(Task task);

    @Mapping(target = "author", source = "author")
    @Mapping(target = "assignee", source = "assignee")
    Task toTask(TaskDto taskDto);

    void updateTaskFromDto(TaskPatchDto dto, @MappingTarget Task task);
    void updateTask(TaskDto taskDto, @MappingTarget Task task);
}
