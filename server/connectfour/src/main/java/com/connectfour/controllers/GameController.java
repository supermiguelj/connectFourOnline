package com.connectfour.controllers;

import com.connectfour.entity.User;
import com.connectfour.entity.UserStats;
import com.connectfour.game.GameEngine;
import com.connectfour.game.GameStatus;
import com.connectfour.repository.UserRepository;
import com.connectfour.repository.UserStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "http://localhost:3000") // Connects to the React frontend
public class GameController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserStatsRepository userStatsRepository;

    private GameEngine gameEngine;

    // Endpoint to create a new game
    @PostMapping("/create")
    public ResponseEntity<String> createGame(@RequestParam String player1Username) {
        User player1 = userRepository.findByUsername(player1Username)
                .orElseThrow(() -> new RuntimeException("Player not found"));
        gameEngine = new GameEngine(player1);
        return ResponseEntity.ok("Game created successfully. Player 1: " + player1Username);
    }

    // Endpoint for player 2 to join the game
    @PostMapping("/join")
    public ResponseEntity<String> joinGame(@RequestParam String player2Username) {
        if (gameEngine == null) {
            return ResponseEntity.badRequest().body("Game not found.");
        }
    
        if (gameEngine.getGameStatus() == GameStatus.IN_PROGRESS || gameEngine.getGameStatus() == GameStatus.FINISHED) {
            return ResponseEntity.badRequest().body("Game has already started or finished.");
        }
    
        User player2 = userRepository.findByUsername(player2Username)
                .orElseThrow(() -> new RuntimeException("Player not found"));
        gameEngine.setPlayer2(player2);
        gameEngine.setGameStatus(GameStatus.IN_PROGRESS);
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
                gameEngine.setGameStatus(GameStatus.FINISHED);

                User loser = (winner == gameEngine.getPlayer1()) ? gameEngine.getPlayer2() : gameEngine.getPlayer1();

                // Update winner stats
                UserStats winnerStats = winner.getUserStats();
                winnerStats.setWins(winnerStats.getWins() + 1);

                // Update loser stats
                UserStats loserStats = loser.getUserStats();
                loserStats.setLosses(loserStats.getLosses() + 1);

                // Recalculate ratios
                winnerStats.setRatio((double) winnerStats.getWins() /
                        Math.max(winnerStats.getLosses(), 1)); // Avoid divide by zero

                loserStats.setRatio((double) loserStats.getWins() /
                        Math.max(loserStats.getLosses(), 1));

                userStatsRepository.save(winnerStats);
                userStatsRepository.save(loserStats);

                return ResponseEntity.ok(winner.getUsername() + " wins the game!");
            }

            if (gameEngine.isBoardFull()) {
                gameEngine.setGameStatus(GameStatus.FINISHED);
                return ResponseEntity.ok("The game ended in a draw!");
            }

            gameEngine.switchTurn();
            return ResponseEntity.ok("Move accepted. It's now " +
                    gameEngine.getCurrentPlayer().getUsername() + "'s turn.");
        } else {
            return ResponseEntity.badRequest().body("Invalid move. Column is full.");
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

    // Example of updating stats after a game
public ResponseEntity<String> updateUserStats(String username, int wins, int losses) {
    User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    UserStats stats = user.getUserStats();
    
    if (stats == null) {
        stats = new UserStats();
        stats.setUsername(username);
        stats.setUser(user);
    }

    stats.setWins(wins);
    stats.setLosses(losses);
    stats.setRatio(losses == 0 ? wins : (double) wins / losses);

    userStatsRepository.save(stats);

    return ResponseEntity.ok("User stats updated successfully.");
}

}
