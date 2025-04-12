package com.connectfour.game;

import com.connectfour.entity.User;
import com.connectfour.game.GameEngine;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    // Create a new game and initialize the game engine
    public GameEngine createGame(User user) {
        return new GameEngine(user);
    }
}
