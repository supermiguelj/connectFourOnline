package com.connectfour.connectfour.AuthController;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.connectfour.connectfour.DTO.LoginRequest;
import com.connectfour.connectfour.Entity.User;
import com.connectfour.connectfour.Repository.UserRepository;

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
            if (checkPassword(payload.getPassword(), user.getPasswordHash())) { //Passoword matches
                return ResponseEntity.ok(Map.of("message", "Login Successful!"));
            } else { // Incorrect Password
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Password");
            }

        } else { // User is not located in database, returns error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    // Used for password encryption validation check
    private boolean checkPassword(String inputPassword, String storedHash) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(inputPassword, storedHash);
    }
     
}
