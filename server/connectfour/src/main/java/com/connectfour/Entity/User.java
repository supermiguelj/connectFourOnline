package com.connectfour.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    private String username;
    private String passwordHash;

    // No-arg constructor required so Hibernate doesn't have a panic attack
    public User() {
        // Empty
    }

    public User(String name, String hashedPassword) {
        username = name;
        passwordHash = hashedPassword;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String newName) {
        username = newName;
    }
    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String newPasswordHash) {
        passwordHash = newPasswordHash;
    }
}