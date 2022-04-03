package com.codinggame.chess.board.score;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.pieces.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckScoreTest {

    @Test
    void shouldReturn100WhenCheck(){
        Board board = new Board("8/8/2k5/8/8/2Q5/8/8");
        int evaluate = CheckScore.evaluate(board, Color.white);
        assertEquals(100,evaluate);
    }

    @Test
    void shouldReturn0WhenNotDoACheck(){
        Board board = new Board("8/8/2k5/8/8/2Q5/8/8");
        int evaluate = CheckScore.evaluate(board, Color.black);
        assertEquals(0,evaluate);
    }

}