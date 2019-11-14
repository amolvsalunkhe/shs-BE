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
import com.gt.shs.model.User;
import com.gt.shs.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	@Autowired
    UserRepository userRepository;

    // Get All Notes
    @GetMapping(path = "/users", produces = "application/json")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
 // Create a new Note
    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }
    // Get a Single Note
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable(value = "id") Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RoleNotFoundException("Role", "id", userId));
    }
    // Update a Note
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable(value = "id") Long userId,
                                            @Valid @RequestBody User userDetails) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RoleNotFoundException("Note", "id", userId));

        user.setName(userDetails.getName());
        user.setStatus(userDetails.getStatus());

        User updatedUser = userRepository.save(user);
        return updatedUser;
    }
    // Delete a Note
    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RoleNotFoundException("Note", "id", userId));
    
        userRepository.delete(user);
    
        return ResponseEntity.ok().build();
    }
}
