package ru.yanmayak.taskmanagementsystem.dto.task;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
public class UserDto {
    private UUID id;
    private String username;
    private String email;
}
