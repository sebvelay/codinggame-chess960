package com.codinggame.chess.board.pieces;

import com.codinggame.chess.board.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CheckTest {

    @Test
    void shouldKnowIfABoardIncludeCheck(){
        Board board = new Board("k2RP3/2R1P3/2P1P3/3P4/8/8/8/8");
        boolean check = board.isDoACheck(Color.white);
        Assertions.assertTrue(check);
    }

    @Test
    void shouldKnowIfNotCheck(){
        Board board = new Board("k3P3/2R1P3/2PRP3/3P4/8/8/8/8");
        boolean check = board.isDoACheck(Color.white);
        Assertions.assertFalse(check);
    }


}
