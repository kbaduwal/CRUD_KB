package com.example.crud.controller;


import com.example.crud.dto.AssignUserRequest;
import com.example.crud.dto.UserBinding;
import com.example.crud.exception.UserNotFoundException;
import com.example.crud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserBinding userBinding) {
        boolean isCreated = userService.createUser(userBinding);

        if (isCreated) {
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        }else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }

    }

    @GetMapping
    public ResponseEntity<List<UserBinding>> getAllUsers() {
        List<UserBinding> usersList = userService.getAllUsers();
        return ResponseEntity.ok(usersList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserBinding> getUserById(@PathVariable Long id) {
        try {
            UserBinding user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        }catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserBinding> updateUserById(@PathVariable Long id,
                                                      @RequestBody UserBinding userBinding)
    {
        try {
            UserBinding updatedDetail = userService.updateUser(id, userBinding);
            return ResponseEntity.ok(updatedDetail);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        try{
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        }catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping("/assign-users")
    public ResponseEntity<String> assignUsersToBook(@RequestBody AssignUserRequest request) {
        String responseMessage = userService.assignUsersToBook(request.getBookId(), request.getUserIds());
        return ResponseEntity.ok(responseMessage);
    }



}
