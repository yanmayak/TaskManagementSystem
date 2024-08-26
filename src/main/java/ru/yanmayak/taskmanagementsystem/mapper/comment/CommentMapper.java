package ru.yanmayak.taskmanagementsystem.mapper.comment;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.yanmayak.taskmanagementsystem.dto.comment.CommentGetDto;
import ru.yanmayak.taskmanagementsystem.entity.Comment;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class CommentMapper {
    public abstract CommentGetDto toDto(Comment comment);
}
