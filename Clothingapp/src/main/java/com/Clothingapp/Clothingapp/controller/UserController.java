package com.Clothingapp.Clothingapp.controller;

import com.Clothingapp.Clothingapp.entity.User;
import com.Clothingapp.Clothingapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    public class YourClass {

        private static final Logger logger = LoggerFactory.getLogger(YourClass.class);

        public void yourMethod() {
            // Some code

            logger.debug("This is a debug message");
            logger.info("This is an info message");
            logger.warn("This is a warning message");
            logger.error("This is an error message");

            // More code
        }
    }

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        try {
            logger.info("Successfully");
            User createdUser = userService.createUser(user);
            return ResponseEntity.ok(createdUser);
        } catch (Exception e) {
            logger.error("Error creating user", e);
            return ResponseEntity.status(500).body("Error creating user");
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        try {
            logger.info("Successfully");
            User user = userService.getUserById(id);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            logger.error("Error getting user by ID", e);
            return ResponseEntity.status(500).body("Error getting user by ID");
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable Long id) {
        try {  logger.info("Successfully");
            User existingUser = userService.getUserById(id);
            if (existingUser == null) {
                return ResponseEntity.notFound().build();
            }
            existingUser.setEmail(user.getEmail());
            existingUser.setName(user.getName());
            User updatedUser = userService.updateUser(existingUser);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            logger.error("Error updating user", e);
            return ResponseEntity.status(500).body("Error updating user");
        }
    }

    @GetMapping("/users")
    public ResponseEntity<Object> getAllUsers() {
        try {
            logger.info("Successfully");
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            logger.error("Error getting all users", e);
            return ResponseEntity.status(500).body("Error getting all users");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            userService.deleteUser(user);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Error deleting user", e);
            return ResponseEntity.status(500).body("Error deleting user");
        }
    }
}
