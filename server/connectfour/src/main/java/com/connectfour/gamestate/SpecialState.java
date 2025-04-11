package com.connectfour.gamestate;

import com.connectfour.constants.SpecialGameCases;
import com.connectfour.models.Player;

public class SpecialState extends GameState{
    
    private SpecialGameCases specialCase;

    public SpecialState(String gameID, Player lastPlayed, SpecialGameCases errorMessage) {
        super(gameID, lastPlayed, -1, -1);
        this.specialCase = errorMessage;
    }

    public SpecialGameCases getSpecialMessage(){
        return this.specialCase;
    }

}
