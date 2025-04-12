package com.connectfour.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "user_stats")
public class UserStats {

    @Id
    private String username; // Use username as the primary key

    @Version
    private int version;

    @OneToOne
    @MapsId
    @JoinColumn(name = "username", referencedColumnName = "username") // Reference username in the user table
    private User user;

    private int wins;
    private int losses;
    private double winRatio;

    // Constructors
    public UserStats() { }

    public UserStats(User user) {
        this.user = user;
        this.username = user.getUsername();
        this.wins = 0;  // Default values
        this.losses = 0;
        this.winRatio = 0.0;
    }

    // Getters and Setters
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
        this.username = user.getUsername();
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
        updateWinRatio();
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
        updateWinRatio();
    }

    public double getWinRatio() {
        return winRatio;
    }

    private void updateWinRatio() {
        if (wins + losses > 0) {
            this.winRatio = (double) wins / (wins + losses);
        } else {
            this.winRatio = 0.0; // Handle case when no games have been played
        }
    }

    public void setRatio(double winRatio) {
        this.winRatio = winRatio;
    }
    
}