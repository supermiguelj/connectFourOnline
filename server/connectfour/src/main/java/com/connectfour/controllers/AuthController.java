package com.connectfour.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.ok;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.connectfour.dto.LoginRequest;
import com.connectfour.dto.RegisterRequest;
import com.connectfour.entity.User;
import com.connectfour.entity.UserStats;
import com.connectfour.repository.UserRepository;
import com.connectfour.utilities.JwtUtil;
import com.connectfour.utilities.PasswordUtil;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // Connects to React
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // Maps the POST request with the /login page, retrieving credentials and directing it
    // To the database
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest payload) {
        Map<String, String> response = new HashMap<>();
        // Retrieves User from the database with same username as payload
        Optional<User> userOpt = userRepository.findByUsername(payload.getUsername());

        if (userOpt.isPresent()) { // User is located in database, handles it
            User user = userOpt.get();

            // Checks for password validation
            if (PasswordUtil.checkPassword(payload.getPassword(), user.getPasswordHash())) { // Password matches
                // Handles token generation for session
                String jwt = JwtUtil.generateToken(user);
                response.put("message", "Login Successful!");
                // Sends the key back to the client
                response.put("token", jwt);
                return ok(response);
            } else { // Incorrect Password
                response.put("UNAUTHORIZED", "Invalid Password");
                return ResponseEntity.badRequest().body(response);
            }

        } else { // User is not located in database, returns error message
            response.put("NOT FOUND", "User not found");
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Maps POST request with /register page, sending new credentials into the users table
    // In the database
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequest payload) {
        Map<String, String> response = new HashMap<>();
        // Checks if username already exists
        if (userRepository.findByUsername(payload.getUsername()).isPresent()) {
            response.put("message", "Username already taken.");
            return ResponseEntity.badRequest().body(response);
        }

        // Username does not exist from here on out

        // Hashes password
        String hashedPassword = PasswordUtil.hashPassword(payload.getPassword());

        // Creates new User
        User newUser = new User(payload.getUsername(), hashedPassword);

        // Create a new stats record and link to user
        UserStats stats = new UserStats();
        stats.setWins(0);
        stats.setLosses(0);
        stats.setRatio(0.0);

        // Ensure User and UserStats are linked correctly
        newUser.setUserStats(stats);
        stats.setUser(newUser); // This establishes the bi-directional relationship

        // Saves User and UserStats to the Database
        userRepository.save(newUser); // This will also save UserStats due to cascading if configured

        response.put("message", "User successfully registered");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/home")
    public ResponseEntity<String> ValidateSession(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer", "");
        // Token authentication fails
        if (!JwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session token has expired");
        }

        // Validates session for user
        String username = JwtUtil.extractUsername(token);
        return ResponseEntity.ok("You are logged on!");
    }
}
