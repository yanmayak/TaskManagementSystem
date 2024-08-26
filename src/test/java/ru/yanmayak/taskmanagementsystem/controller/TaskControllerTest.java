package ru.yanmayak.taskmanagementsystem.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.yanmayak.taskmanagementsystem.dto.task.TaskFilterDto;
import ru.yanmayak.taskmanagementsystem.service.task.TaskService;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {
//    @Mock
//    private TaskService taskService;
//
//    @InjectMocks
//    private TaskController taskController;
//
//    @Test
////    void testCreateTask() {
//        TaskDto taskDto = new TaskDto();
//        TaskDto createdTask = new TaskDto();
//
//        when(taskService.createTask(taskDto)).thenReturn(createdTask);
//
//        ResponseEntity<TaskDto> response = taskController.createTask(taskDto);
//
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertEquals(createdTask, response.getBody());
//    }
//
//    @Test
//    void testUpdateTask() {
//        UUID taskId = UUID.randomUUID();
//        TaskPatchDto taskPatchDto = new TaskPatchDto();
//        TaskDto updatedTask = new TaskDto();
//
//        when(taskService.patchTask(taskId, taskPatchDto)).thenReturn(updatedTask);
//
//        ResponseEntity<TaskDto> response = taskController.updateTask(taskId, taskPatchDto);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(updatedTask, response.getBody());
//    }
//
//    @Test
//    void testFullUpdateTask() {
//        UUID taskId = UUID.randomUUID();
//        TaskDto taskDto = new TaskDto();
//        TaskDto updatedTask = new TaskDto();
//
//        when(taskService.updateTask(taskId, taskDto)).thenReturn(updatedTask);
//
//        ResponseEntity<TaskDto> response = taskController.updateTask(taskId, taskDto);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(updatedTask, response.getBody());
//    }
//
//    @Test
//    void testDeleteTask() {
//        UUID taskId = UUID.randomUUID();
//
//        ResponseEntity<Void> response = taskController.deleteTask(taskId);
//
//        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
//    }
//
//    @Test
//    void testGetAllTasks() {
//        List<TaskDto> tasks = List.of(new TaskDto(), new TaskDto());
//
//        when(taskService.getAllTasks()).thenReturn(tasks);
//
//        ResponseEntity<List<TaskDto>> response = taskController.getAllTasks();
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(tasks, response.getBody());
//    }
//
//    @Test
//    void testGetTasks() {
//        TaskFilterDto filterDto = new TaskFilterDto();
//        PageRequest pageable = PageRequest.of(0, 10);
//        List<TaskDto> tasks = List.of(new TaskDto(), new TaskDto());
//        Page<TaskDto> taskPage = new PageImpl<>(tasks, pageable, tasks.size());
//
//        when(taskService.getTasks(filterDto, pageable)).thenReturn(taskPage);
//
//        Page<TaskDto> response = taskController.getTasks(filterDto, pageable);
//
//        assertEquals(taskPage, response);
//    }
}
