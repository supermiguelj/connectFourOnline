package com.connectfour.dto;

//Used for Player 2 connection

public class GameMessage {
    private String player;
    private String playerId;

    public GameMessage() {
    }

    public GameMessage(String player, String playerId) {
        this.player = player;
        this.playerId = playerId;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
}
