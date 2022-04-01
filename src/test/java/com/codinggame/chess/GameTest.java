package com.codinggame.chess;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class GameTest {

    @Test
    void test(){
        ArrayList<String> legalMovesFromGame = new ArrayList<>();
        legalMovesFromGame.add("");
        Game game = new Game();
        game.play("nrqkrnbb/pppppppp/8/8/8/8/PPPPPPPP/NRQKRNBB","w",new ArrayList<>());
    }
}
