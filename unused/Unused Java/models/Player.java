package com.connectfour.models;

import com.connectfour.constants.GamePiece;

import jakarta.persistence.*;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String playerID;
    private String username;
    private GamePiece gamePiece;
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private PlayerStatistics playerStatistics;

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
        this.playerStatistics = new PlayerStatistics(this);
    }

    public Player() {
        // Default constructor required by JPA
    }

    // Getters and Setters
    public String getPlayerID() {
        return playerID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public GamePiece getGamePiece() {
        return gamePiece;
    }

    public void setGamePiece(GamePiece gamePiece) {
        this.gamePiece = gamePiece;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PlayerStatistics getPlayerStatistics() {
        return playerStatistics;
    }

    public void setPlayerStatistics(PlayerStatistics playerStatistics) {
        this.playerStatistics = playerStatistics;
    }

    // equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (playerID != null ? !playerID.equals(player.playerID) : player.playerID != null) return false;
        if (username != null ? !username.equals(player.username) : player.username != null) return false;
        if (gamePiece != player.gamePiece) return false;
        if (password != null ? !password.equals(player.password) : player.password != null) return false;
        return playerStatistics != null ? playerStatistics.equals(player.playerStatistics) : player.playerStatistics == null;
    }

    @Override
    public int hashCode() {
        int result = playerID != null ? playerID.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (gamePiece != null ? gamePiece.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (playerStatistics != null ? playerStatistics.hashCode() : 0);
        return result;
    }

    // toString()
    @Override
    public String toString() {
        return "Player{" +
                "playerID='" + playerID + '\'' +
                ", username='" + username + '\'' +
                ", gamePiece=" + gamePiece +
                ", password='[PROTECTED]'" +
                ", playerStatistics=" + playerStatistics +
                '}';
    }
}
