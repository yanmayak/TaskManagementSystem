package ru.yanmayak.taskmanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.yanmayak.taskmanagementsystem.dto.user.UserDto;
import ru.yanmayak.taskmanagementsystem.entity.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserDto toUserDto(User user);
    User toUser(UserDto userDto);
}
