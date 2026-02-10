package com.auca.restfulapi.user.controller;

import com.auca.restfulapi.user.model.ApiResponse;
import com.auca.restfulapi.user.model.UserProfile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {
    
    private List<UserProfile> users = new ArrayList<>();
    private Long nextId = 6L;

    public UserProfileController() {
        users.add(new UserProfile(1L, "john_doe", "john@example.com", "John Doe", 25, "USA", "Software developer", true));
        users.add(new UserProfile(2L, "jane_smith", "jane@example.com", "Jane Smith", 28, "Canada", "Data scientist", true));
        users.add(new UserProfile(3L, "mike_wilson", "mike@example.com", "Mike Wilson", 30, "UK", "Product manager", false));
        users.add(new UserProfile(4L, "sarah_jones", "sarah@example.com", "Sarah Jones", 22, "USA", "Designer", true));
        users.add(new UserProfile(5L, "david_brown", "david@example.com", "David Brown", 35, "Australia", "Engineer", true));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserProfile>>> getAllUsers() {
        ApiResponse<List<UserProfile>> response = new ApiResponse<>(true, "Users retrieved successfully", users);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserProfile>> getUserById(@PathVariable Long userId) {
        return users.stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst()
                .map(user -> ResponseEntity.ok(new ApiResponse<>(true, "User found", user)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false, "User not found", null)));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<ApiResponse<UserProfile>> getUserByUsername(@PathVariable String username) {
        return users.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .map(user -> ResponseEntity.ok(new ApiResponse<>(true, "User found", user)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false, "User not found", null)));
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<ApiResponse<List<UserProfile>>> getUsersByCountry(@PathVariable String country) {
        List<UserProfile> result = users.stream()
                .filter(user -> user.getCountry().equalsIgnoreCase(country))
                .toList();
        ApiResponse<List<UserProfile>> response = new ApiResponse<>(true, "Users from " + country + " retrieved", result);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/age-range")
    public ResponseEntity<ApiResponse<List<UserProfile>>> getUsersByAgeRange(
            @RequestParam int min,
            @RequestParam int max) {
        List<UserProfile> result = users.stream()
                .filter(user -> user.getAge() >= min && user.getAge() <= max)
                .toList();
        ApiResponse<List<UserProfile>> response = new ApiResponse<>(true, "Users in age range retrieved", result);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<UserProfile>>> getActiveUsers(@RequestParam boolean active) {
        List<UserProfile> result = users.stream()
                .filter(user -> user.isActive() == active)
                .toList();
        String message = active ? "Active users retrieved" : "Inactive users retrieved";
        ApiResponse<List<UserProfile>> response = new ApiResponse<>(true, message, result);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserProfile>> createUser(@RequestBody UserProfile user) {
        user.setUserId(nextId++);
        users.add(user);
        ApiResponse<UserProfile> response = new ApiResponse<>(true, "User profile created successfully", user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserProfile>> updateUser(@PathVariable Long userId, @RequestBody UserProfile updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(userId)) {
                updatedUser.setUserId(userId);
                users.set(i, updatedUser);
                ApiResponse<UserProfile> response = new ApiResponse<>(true, "User profile updated successfully", updatedUser);
                return ResponseEntity.ok(response);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, "User not found", null));
    }

    @PatchMapping("/{userId}/activate")
    public ResponseEntity<ApiResponse<UserProfile>> activateUser(@PathVariable Long userId) {
        for (UserProfile user : users) {
            if (user.getUserId().equals(userId)) {
                user.setActive(true);
                ApiResponse<UserProfile> response = new ApiResponse<>(true, "User activated successfully", user);
                return ResponseEntity.ok(response);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, "User not found", null));
    }

    @PatchMapping("/{userId}/deactivate")
    public ResponseEntity<ApiResponse<UserProfile>> deactivateUser(@PathVariable Long userId) {
        for (UserProfile user : users) {
            if (user.getUserId().equals(userId)) {
                user.setActive(false);
                ApiResponse<UserProfile> response = new ApiResponse<>(true, "User deactivated successfully", user);
                return ResponseEntity.ok(response);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, "User not found", null));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long userId) {
        boolean removed = users.removeIf(user -> user.getUserId().equals(userId));
        if (removed) {
            ApiResponse<Void> response = new ApiResponse<>(true, "User deleted successfully", null);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, "User not found", null));
    }
}
