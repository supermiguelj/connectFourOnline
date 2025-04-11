package com.connectfour.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.connectfour.constants.GameStatus;
import com.connectfour.models.Game;

@Repository
public interface GameRepository extends CrudRepository<Game, String> {

    List<Game> findGameEntitiesByGameStatus(GameStatus gameStatus);

}
