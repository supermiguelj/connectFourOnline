package com.connectfour.game;

public enum GameStatus {
    NEW("Game initialized."),
    WAITING_FOR_PLAYER("Waiting for another player to join."),
    IN_PROGRESS("Game is in progress."),
    PLAYER_ONE_WINS("Player One wins!"),
    PLAYER_TWO_WINS("Player Two wins!"),
    DRAW("Game ended in a draw."),
    INVALID_MOVE("That move is not allowed."),
    GAME_OVER("The game is already over.");

    private final String message;

    GameStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
