package ru.yanmayak.taskmanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import ru.yanmayak.taskmanagementsystem.dto.task.CommentDto;
import ru.yanmayak.taskmanagementsystem.entity.Comment;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentMapper {
//    @Mapping(target = "taskId", source = "task.id")
//    @Mapping(target = "authorId", source = "author.id")
//    CommentDto toCommentDto(Comment comment);
//
//    @Mapping(target = "task", source = "taskId")
//    @Mapping(target = "author", source = "authorId")
//    Comment toComment(CommentDto commentDto);
//
    @Mapping(target = "task", source = "task")
    @Mapping(target = "author", source = "author")
    void updateCommentFromDto(CommentDto commentDTO, @MappingTarget Comment comment);

    @Mapping(target = "task", source = "task")
    @Mapping(target = "author", source = "author")
    CommentDto toCommentDto(Comment comment);

    @Mapping(target = "task", source = "task")
    @Mapping(target = "author", source = "author")
    Comment toComment(CommentDto commentDTO);


}
