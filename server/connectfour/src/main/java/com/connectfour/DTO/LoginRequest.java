package com.connectfour.dto;

// THIS IS THE PAYLOAD for /login
// This is a Data-Transfer Object used to send the payload from the React frontend to the
// Spring Boot backend
public class LoginRequest {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
