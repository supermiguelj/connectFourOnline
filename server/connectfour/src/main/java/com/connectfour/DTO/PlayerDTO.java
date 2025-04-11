package com.connectfour.dto;

import com.connectfour.constants.GamePiece;
import com.connectfour.models.Player;
import com.connectfour.models.PlayerStatistics;


public class PlayerDTO {
    private String playerID;
    private String username;
    private GamePiece gamePiece;
    private PlayerStatistics playerStatistics;

    public PlayerDTO(Player player){
        this.playerID = player.getPlayerID();
        this.username = player.getUsername();
        this.gamePiece = player.getGamePiece();
        this.playerStatistics = player.getPlayerStatistics();
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
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

    public PlayerStatistics getPlayerStatistics() {
        return playerStatistics;
    }

    public void setPlayerStatistics(PlayerStatistics playerStatistics) {
        this.playerStatistics = playerStatistics;
    }
}
