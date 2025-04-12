package com.connectfour.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_stats")
public class UserStats {

    @Id
    private String username; // Use username as the primary key

    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username") // Reference username in the user table
    private User user;

    private int wins;
    private int losses;
    private double winRatio;

    // Constructors, Getters, and Setters
    public UserStats() { }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public double getWinRatio() {
        return winRatio;
    }

    public void setRatio(double winRatio) {
        this.winRatio = winRatio;
    }
}
