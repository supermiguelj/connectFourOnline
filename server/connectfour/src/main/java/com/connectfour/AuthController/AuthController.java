package com.connectfour.AuthController;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.connectfour.Repository.UserRepository;;

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
    public ResponseEntity<?> login(@RequestBody LoginRequest payload) {
        // Retrieves User from the database with same username as payload
        Optional<User> userOpt = userRepository.findByUsername(payload.getUsername());

        if (userOpt.isPresent()) { // User is located in database, handles it
            User user = userOpt.get();

            // Checks for password validation
            if (PasswordUtil.checkPassword(payload.getPassword(), user.getPasswordHash())) { //Passoword matches
                return ResponseEntity.ok(Map.of("message", "Login Successful!"));
            } else { // Incorrect Password
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Password");
            }

        } else { // User is not located in database, returns error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    // Maps POST request with /register page, sending new credentials into the users table
    // In the database
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest payload) {
        // Checks if username already exists
        if (userRepository.findByUsername(payload.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username is already taken");
        }

        // Username does not exist from here on out

        // Hashes password
        String hashedPassword = PasswordUtil.hashPassword(payload.getPassword());

        // Creates new User
        User newUser = new User(payload.getUsername(), hashedPassword);

        // Saves to Database
        userRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body("User successfully registered!");
    }

    @GetMapping("/test-cors")
public ResponseEntity<String> testCors() {
    return ResponseEntity.ok("CORS is working!");
}

}
