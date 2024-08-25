package ru.yanmayak.taskmanagementsystem.dto.task;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskFilterDto {
    private String title;
    private UUID assigneeId;
    private UUID authorId;
    private String priority;
    private String status;

    @NotBlank
    @Min(0)
    private Integer page;

    @NotBlank
    @Size(min = 1, max = 100)
    private Integer size;
}
