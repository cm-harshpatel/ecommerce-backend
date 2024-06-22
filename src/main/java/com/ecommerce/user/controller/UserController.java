package com.ecommerce.user.controller;

import com.ecommerce.user.model.User;
import com.ecommerce.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:3000") // Replace with your frontend URL
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User registeredUser = userService.register(user);
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Registration failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:3000") // Replace with your frontend URL
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            System.out.println("Received login request: " + loginRequest.getEmail() + ", " + loginRequest.getPassword());

            User existingUser = userService.findByEmail(loginRequest.getEmail());
            if (existingUser == null) {
                System.out.println("User not found with email: " + loginRequest.getEmail());
                return new ResponseEntity<>("User not found with email: " + loginRequest.getEmail(), HttpStatus.NOT_FOUND);
            }

            // Here you should ideally use a password encoder to compare passwords securely
            if (existingUser.getPassword().equals(loginRequest.getPassword())) {
                System.out.println("Login successful for user: " + existingUser.getUsername());
                return ResponseEntity.ok("Login successful!");
            } else {
                System.out.println("Invalid password for user: " + existingUser.getUsername());
                return new ResponseEntity<>("Invalid password", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            System.err.println("Login failed: " + e.getMessage());
            return new ResponseEntity<>("Login failed: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
