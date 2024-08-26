package ru.yanmayak.taskmanagementsystem.mapper.task;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yanmayak.taskmanagementsystem.dto.task.TaskCreateDto;
import ru.yanmayak.taskmanagementsystem.dto.task.TaskGetDto;
import ru.yanmayak.taskmanagementsystem.entity.Task;
import ru.yanmayak.taskmanagementsystem.entity.User;
import ru.yanmayak.taskmanagementsystem.repository.UserRepository;

import java.util.UUID;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class TaskMapper {
    @Autowired
    private UserRepository userRepository;

    @Mapping(target = "author", source = "authorId", qualifiedByName = "mapUser")
    @Mapping(target = "assignee", source = "assigneeId", qualifiedByName = "mapUser")
    public abstract Task mapToDomain(TaskCreateDto task);

    @Mapping(target = "author", source = "author")
    @Mapping(target = "assignee", source = "assignee")
    public abstract TaskGetDto mapToDto(Task task);

    @Named("mapUser")
    public User mapUser(UUID id) {
        if (id == null) {
            return null;
        }
        return userRepository.findById(id).
                orElse(null);
    }
}
