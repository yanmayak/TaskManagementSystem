package ru.yanmayak.taskmanagementsystem.repository;

import org.springframework.stereotype.Repository;
import ru.yanmayak.taskmanagementsystem.config.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
