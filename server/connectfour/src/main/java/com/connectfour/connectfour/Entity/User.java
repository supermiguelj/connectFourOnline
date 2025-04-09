package com.connectfour.connectfour.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    private String username;
    private String passwordHash;

    public String getUsername() {
        return username;
    }
    public void setUsername(String newName) {
        username = newName;
    }
    public String getPasswordHash() {
        return passwordHash;
    }
}