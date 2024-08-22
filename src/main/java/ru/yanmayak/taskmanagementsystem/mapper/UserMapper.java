package ru.yanmayak.taskmanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import ru.yanmayak.taskmanagementsystem.entity.User;

import java.util.UUID;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    default User toUser(UUID id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }

    default UUID fromUser(User user) {
        return user != null ? user.getId() : null;
    }
}
