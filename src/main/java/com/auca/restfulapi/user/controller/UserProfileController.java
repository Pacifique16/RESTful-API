package com.auca.restfulapi.user.controller;

import com.auca.restfulapi.user.model.ApiResponse;
import com.auca.restfulapi.user.model.UserProfile;
import com.auca.restfulapi.user.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {
    
    @Autowired
    private UserProfileRepository userProfileRepository;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserProfile>>> getAllUsers() {
        List<UserProfile> users = userProfileRepository.findAll();
        ApiResponse<List<UserProfile>> response = new ApiResponse<>(true, "Users retrieved successfully", users);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserProfile>> getUserById(@PathVariable Long userId) {
        return userProfileRepository.findById(userId)
                .map(user -> ResponseEntity.ok(new ApiResponse<>(true, "User found", user)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false, "User not found", null)));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<ApiResponse<UserProfile>> getUserByUsername(@PathVariable String username) {
        return userProfileRepository.findByUsernameIgnoreCase(username)
                .map(user -> ResponseEntity.ok(new ApiResponse<>(true, "User found", user)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false, "User not found", null)));
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<ApiResponse<List<UserProfile>>> getUsersByCountry(@PathVariable String country) {
        List<UserProfile> result = userProfileRepository.findByCountryIgnoreCase(country);
        ApiResponse<List<UserProfile>> response = new ApiResponse<>(true, "Users from " + country + " retrieved", result);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/age-range")
    public ResponseEntity<ApiResponse<List<UserProfile>>> getUsersByAgeRange(@RequestParam int min, @RequestParam int max) {
        List<UserProfile> result = userProfileRepository.findByAgeBetween(min, max);
        ApiResponse<List<UserProfile>> response = new ApiResponse<>(true, "Users in age range retrieved", result);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<UserProfile>>> getActiveUsers(@RequestParam boolean active) {
        List<UserProfile> result = userProfileRepository.findByActive(active);
        String message = active ? "Active users retrieved" : "Inactive users retrieved";
        ApiResponse<List<UserProfile>> response = new ApiResponse<>(true, message, result);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserProfile>> createUser(@RequestBody UserProfile user) {
        if (userProfileRepository.findByUsernameIgnoreCase(user.getUsername()).isPresent()) {
            ApiResponse<UserProfile> response = new ApiResponse<>(false, "User with this username already exists", null);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        UserProfile saved = userProfileRepository.save(user);
        ApiResponse<UserProfile> response = new ApiResponse<>(true, "User profile created successfully", saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserProfile>> updateUser(@PathVariable Long userId, @RequestBody UserProfile updatedUser) {
        if (userProfileRepository.existsById(userId)) {
            updatedUser.setUserId(userId);
            UserProfile saved = userProfileRepository.save(updatedUser);
            ApiResponse<UserProfile> response = new ApiResponse<>(true, "User profile updated successfully", saved);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, "User not found", null));
    }

    @PatchMapping("/{userId}/activate")
    public ResponseEntity<ApiResponse<UserProfile>> activateUser(@PathVariable Long userId) {
        return userProfileRepository.findById(userId)
                .map(user -> {
                    user.setActive(true);
                    UserProfile saved = userProfileRepository.save(user);
                    ApiResponse<UserProfile> response = new ApiResponse<>(true, "User activated successfully", saved);
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false, "User not found", null)));
    }

    @PatchMapping("/{userId}/deactivate")
    public ResponseEntity<ApiResponse<UserProfile>> deactivateUser(@PathVariable Long userId) {
        return userProfileRepository.findById(userId)
                .map(user -> {
                    user.setActive(false);
                    UserProfile saved = userProfileRepository.save(user);
                    ApiResponse<UserProfile> response = new ApiResponse<>(true, "User deactivated successfully", saved);
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false, "User not found", null)));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long userId) {
        if (userProfileRepository.existsById(userId)) {
            userProfileRepository.deleteById(userId);
            ApiResponse<Void> response = new ApiResponse<>(true, "User deleted successfully", null);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, "User not found", null));
    }
}
