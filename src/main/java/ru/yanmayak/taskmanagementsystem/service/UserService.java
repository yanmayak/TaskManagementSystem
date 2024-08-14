package ru.yanmayak.taskmanagementsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.yanmayak.taskmanagementsystem.model.User;
import ru.yanmayak.taskmanagementsystem.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public User save(User user) {
        return repository.save(user);
    }

    public User create(User user) {
        if(repository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if(repository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
    return save(user);
    }

    public User getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public User getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }
}
