package com.connectfour.game;

import com.connectfour.entity.User;

public class GameEngine {

    private static final int ROWS = 6;
    private static final int COLS = 7;
    private int[][] board = new int[ROWS][COLS]; // 0 = empty, 1 = player1, 2 = player2
    private User player1;
    private User player2;
    private User currentPlayer;
    private GameStatus gameStatus;

    public GameEngine(User player1) {
        this.player1 = player1;
        this.currentPlayer = player1; // Player 1 starts
        this.gameStatus = GameStatus.NEW;
        resetBoard();
    }

    // Reset the board to the initial state
    private void resetBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = 0; // All cells are empty at the start
            }
        }
    }

    // Add a game piece to the board
    public boolean makeMove(int col) {
        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][col] == 0) {
                board[row][col] = currentPlayer == player1 ? 1 : 2;
                return true;
            }
        }
        return false; // Column is full
    }

    // Switch turns between players
    public void switchTurn() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    // Check if there's a winner
    public User checkWinner() {
        // Horizontal, vertical, and diagonal checks for winner
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col] == 0) continue;

                if (checkDirection(row, col, 1, 0) || // Horizontal
                    checkDirection(row, col, 0, 1) || // Vertical
                    checkDirection(row, col, 1, 1) || // Diagonal /
                    checkDirection(row, col, 1, -1)) { // Diagonal \
                    return (board[row][col] == 1) ? player1 : player2;
                }
            }
        }
        return null; // No winner yet
    }

    // Check in a specific direction for a winning line (4 in a row)
    private boolean checkDirection(int row, int col, int dRow, int dCol) {
        int count = 0;
        int player = board[row][col];
        for (int i = 0; i < 4; i++) {
            int r = row + i * dRow;
            int c = col + i * dCol;
            if (r >= 0 && r < ROWS && c >= 0 && c < COLS && board[r][c] == player) {
                count++;
            } else {
                break;
            }
        }
        return count == 4;
    }

    // Check if the board is full
    public boolean isBoardFull() {
        for (int col = 0; col < COLS; col++) {
            if (board[0][col] == 0) {
                return false;
            }
        }
        return true;
    }

    // Getters and Setters for game state
    public int[][] getBoard() {
        return board;
    }

    public User getCurrentPlayer() {
        return currentPlayer;
    }

    public void setPlayer2(User player2) {
        this.player2 = player2;
    }

    public User getPlayer1() {
        return player1;
    }
    
    public User getPlayer2() {
        return player2;
    }
    

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }
}
