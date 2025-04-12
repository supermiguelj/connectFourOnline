package com.connectfour.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_stats")
public class UserStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private int wins;
    private int losses;
    private double winRatio;

    // Constructors
    public UserStats() {}
    public UserStats(User user) {
        this.user = user;
        this.wins = 0;
        this.losses = 0;
        this.winRatio = 0.0;
    }

    // Getters and Setters
    public void incrementWins() {
        this.wins++;
        recalculateRatio();
    }

    public void incrementLosses() {
        this.losses++;
        recalculateRatio();
    }

    private void recalculateRatio() {
        if (losses == 0) {
            winRatio = wins;
        } else {
            winRatio = (double) wins / losses;
        }
    }

    // other getters/setters
}
