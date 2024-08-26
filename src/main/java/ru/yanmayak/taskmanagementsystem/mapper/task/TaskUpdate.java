package ru.yanmayak.taskmanagementsystem.mapper.task;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yanmayak.taskmanagementsystem.dto.task.TaskUpdateDto;
import ru.yanmayak.taskmanagementsystem.entity.Task;
import ru.yanmayak.taskmanagementsystem.entity.User;

import java.util.UUID;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL
)
public abstract class TaskUpdate {
    @Autowired
    private TaskMapper taskMapper;

    @Mapping(
            target = "assignee",
            source = "assigneeId",
            qualifiedByName = "mapUser"
    )
    public abstract void updateTaskFromDto(@MappingTarget Task task, TaskUpdateDto taskUpdateDto);

    @Named("mapUser")
    protected User mapAssignee(UUID assigneeId) {
        return taskMapper.mapUser(assigneeId);
    }
}
