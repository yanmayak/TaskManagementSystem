package ru.yanmayak.taskmanagementsystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.yanmayak.taskmanagementsystem.entity.Task;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID>, JpaSpecificationExecutor<Task> {
    Page<Task> findByAuthor(UUID authorId, Pageable pageable);
    Page<Task> findByAssignee(UUID assigneeId, Pageable pageable);
    Page<Task> findByStatus(String status, Pageable pageable);
    Page<Task> findByPriority(String priority, Pageable pageable);
    @Query("SELECT t FROM Task t WHERE t.title LIKE %:title%")
    Page<Task> findByTitleContaining(String title, Pageable pageable);
}
