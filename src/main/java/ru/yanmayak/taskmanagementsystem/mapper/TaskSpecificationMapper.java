package ru.yanmayak.taskmanagementsystem.mapper;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.yanmayak.taskmanagementsystem.dto.task.TaskFilterDto;
import ru.yanmayak.taskmanagementsystem.entity.Task;
import java.util.UUID;

@Component
public class TaskSpecificationMapper {
    public Specification<Task> toSpecification(TaskFilterDto filterDto) {
        return Specification.where(titleContains(filterDto.getTitle()))
                .and(assigneeEquals(filterDto.getAssigneeId()))
                .and(authorEquals(filterDto.getAuthorId()))
                .and(priorityEquals(filterDto.getPriority()))
                .and(statusEquals(filterDto.getStatus()));
    }

    private Specification<Task> titleContains(String title) {
        return (root, query, cb) -> title == null ? null : cb.like(root.get("title"), "%" + title + "%");
    }

    private Specification<Task> assigneeEquals(UUID assigneeId) {
        return (root, query, cb) -> assigneeId == null ? null : cb.equal(root.get("assignee").get("id"), assigneeId);
    }

    private Specification<Task> authorEquals(UUID authorId) {
        return (root, query, cb) -> authorId == null ? null : cb.equal(root.get("author").get("id"), authorId);
    }

    private Specification<Task> priorityEquals(String priority) {
        return (root, query, cb) -> priority == null ? null : cb.equal(root.get("priority"), priority);
    }

    private Specification<Task> statusEquals(String status) {
        return (root, query, cb) -> status == null ? null : cb.equal(root.get("status"), status);
    }
}
