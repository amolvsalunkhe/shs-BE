package com.gt.shs.controller;

import com.gt.shs.exceptions.RoleNotFoundException;
import com.gt.shs.model.Role;
import com.gt.shs.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/role")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoleController {

    @Autowired
    RoleRepository roleRepository;

    // Get All Notes
    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
    // Create a new Note
    @PostMapping("/roles")
    public Role createNote(@Valid @RequestBody Role role) {
        return roleRepository.save(role);
    }
    // Get a Single Note
    @GetMapping("/roles/{id}")
    public Role getNoteById(@PathVariable(value = "id") Long roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException("Role", "id", roleId));
    }
    // Update a Note
    @PutMapping("/notes/{id}")
    public Role updateNote(@PathVariable(value = "id") Long roleId,
                                            @Valid @RequestBody Role roleDetails) {

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException("Note", "id", roleId));

        role.setName(roleDetails.getName());
        role.setStatus(roleDetails.getStatus());

        Role updatedRole = roleRepository.save(role);
        return updatedRole;
    }
    // Delete a Note
    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException("Note", "id", roleId));
    
        roleRepository.delete(role);
    
        return ResponseEntity.ok().build();
    }
}