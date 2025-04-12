package com.connectfour.controllers;

import com.connectfour.entity.User;
import com.connectfour.game.GameEngine;
import com.connectfour.game.GameStatus;
import com.connectfour.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "http://localhost:3000") // Connects to the React frontend
public class GameController {

    @Autowired
    private UserRepository userRepository;

    private GameEngine gameEngine;

    // Endpoint to create a new game
    @PostMapping("/create")
    public ResponseEntity<String> createGame(@RequestParam String player1Username) {
        User player1 = userRepository.findByUsername(player1Username).orElseThrow(() -> new RuntimeException("Player not found"));
        gameEngine = new GameEngine(player1); // Start the game with player 1
        return ResponseEntity.ok("Game created successfully. Player 1: " + player1Username);
    }

    // Endpoint for player 2 to join the game
    @PostMapping("/join")
    public ResponseEntity<String> joinGame(@RequestParam String player2Username) {
        if (gameEngine == null) {
            return ResponseEntity.badRequest().body("Game not found.");
        }

        User player2 = userRepository.findByUsername(player2Username).orElseThrow(() -> new RuntimeException("Player not found"));
        gameEngine.setPlayer2(player2); // Set player 2 in the game
        gameEngine.setGameStatus(GameStatus.IN_PROGRESS); // Game is now in progress
        return ResponseEntity.ok("Player 2 joined the game. Game is now in progress.");
    }

    // Endpoint to make a move
    @PostMapping("/move")
    public ResponseEntity<String> makeMove(@RequestParam int col) {
        if (gameEngine == null) {
            return ResponseEntity.badRequest().body("Game has not been started.");
        }
    
        if (gameEngine.makeMove(col)) {
            User winner = gameEngine.checkWinner();
            if (winner != null) {
                GameStatus status = winner.equals(gameEngine.getPlayer1()) ? GameStatus.PLAYER_ONE_WINS : GameStatus.PLAYER_TWO_WINS;
                gameEngine.setGameStatus(status);
                return ResponseEntity.ok(status.getMessage());
            }
    
            if (gameEngine.isBoardFull()) {
                gameEngine.setGameStatus(GameStatus.DRAW);
                return ResponseEntity.ok(GameStatus.DRAW.getMessage());
            }
    
            gameEngine.switchTurn(); // Switch to the other player's turn
            return ResponseEntity.ok("Move accepted. It's now " + gameEngine.getCurrentPlayer().getUsername() + "'s turn.");
        } else {
            return ResponseEntity.badRequest().body(GameStatus.INVALID_MOVE.getMessage());
        }
    }
    

    // Endpoint to get the current game state
    @GetMapping("/state")
    public ResponseEntity<Object> getGameState() {
        if (gameEngine == null) {
            return ResponseEntity.badRequest().body("Game not found.");
        }

        return ResponseEntity.ok(gameEngine.getBoard());
    }
}
