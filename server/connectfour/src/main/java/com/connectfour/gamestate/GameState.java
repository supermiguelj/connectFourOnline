package com.connectfour.gamestate;

import com.connectfour.constants.GamePiece;
import com.connectfour.models.Player;

public class GameState {

    private String gameID;
    private int pieceRow;
    private int pieceCol;
    private String lastPlayedID;
    private GamePiece lastPlayedGamePiece;
    private String lastPlayedUsername;

    public GameState(String gameID, Player lastPlayed, int pieceRow, int pieceCol) {
        this.gameID = gameID;
        this.lastPlayedID = lastPlayed.getPlayerID();
        this.lastPlayedGamePiece = lastPlayed.getGamePiece();
        this.lastPlayedUsername = lastPlayed.getUsername();
        this.pieceCol = pieceCol;
        this.pieceRow = pieceRow;
    }

    public String getGameID() {
        return this.gameID;
    }
}
