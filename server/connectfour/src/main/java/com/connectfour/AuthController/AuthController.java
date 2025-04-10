package com.connectfour.AuthController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.connectfour.DTO.LoginRequest;
import com.connectfour.DTO.RegisterRequest;
import com.connectfour.Entity.User;
import com.connectfour.PasswordUtil.PasswordUtil;
import com.connectfour.Repository.UserRepository;
;

// This is the REST API that uses the DTO as a payload for sending it as a POST request to
// The backend 
@RestController
//RequestMapping("/api/auth")
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
            if (PasswordUtil.checkPassword(payload.getPassword(), user.getPasswordHash())) { //Passoword matches
                response.put("message", "Login Successful!");
                return ResponseEntity.ok(response);
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
        for (int i = 0; i < 5; i++)
            System.out.println("1");

        // Username does not exist from here on out

        // Hashes password
        String hashedPassword = PasswordUtil.hashPassword(payload.getPassword());
        for (int i = 0; i < 5; i++)
            System.out.println("2");

        // Creates new User
        User newUser = new User(payload.getUsername(), hashedPassword);
        for (int i = 0; i < 5; i++)
            System.out.println("3");

        // Saves to Database
        userRepository.save(newUser);
        for (int i = 0; i < 5; i++)
            System.out.println("4");

        response.put("message", "User successfully registered");
        return ResponseEntity.ok(response);

    }

    @GetMapping("/home")
    public ResponseEntity<String> testCors() {
        return ResponseEntity.ok("You are logged on!");
    }

}
