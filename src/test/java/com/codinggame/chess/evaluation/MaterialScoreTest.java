package com.codinggame.chess.evaluation;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.pieces.Color;
import com.codinggame.chess.board.score.MaterialScore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MaterialScoreTest {

    @Test
    void returnValueForColor(){
        Board board = new Board("8/8/8/8/8/2Q1P3/8/8");
        int evaluate = MaterialScore.evaluate(board, Color.white);
        assertEquals(10000,evaluate);
    }
}
