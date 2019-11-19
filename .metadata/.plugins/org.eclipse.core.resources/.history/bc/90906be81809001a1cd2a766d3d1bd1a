package com.gt.shs.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gt.shs.exceptions.RoleNotFoundException;
import com.gt.shs.model.Task;
import com.gt.shs.repository.TaskRepository;

@RestController
@RequestMapping("/api/task")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TaskController {
	@Autowired
    TaskRepository taskRepository;

    // Get All Tasks
    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    // Create a new Note
    @PostMapping("/tasks")
    public Task createTask(@Valid @RequestBody Task task) {
        return taskRepository.save(task);
    }
    // Get a Single Note
    @GetMapping("/tasks/{id}")
    public Task getTaskById(@PathVariable(value = "id") Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new RoleNotFoundException("Role", "id", taskId));
    }
    // Update a Note
    @PutMapping("/tasks/{id}")
    public Task updateNote(@PathVariable(value = "id") Long taskId,
                                            @Valid @RequestBody Task taskDetails) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RoleNotFoundException("Note", "id", taskId));

        task.setName(taskDetails.getName());
        task.setStatus(taskDetails.getStatus());

        Task updatedTask = taskRepository.save(task);
        return updatedTask;
    }
    // Delete a Note
    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RoleNotFoundException("Note", "id", taskId));
    
        taskRepository.delete(task);
    
        return ResponseEntity.ok().build();
    }
}
