package com.codinggame.chess.evaluation;

import com.codinggame.chess.Chrono;
import com.codinggame.chess.Constant;
import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Move;
import com.codinggame.chess.board.pieces.Color;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BishopPositionTest {

    // 8/8/8/8/8/8/PPPPPPPP/6B1

    Board board;

    @BeforeEach
    void setUp() {

        Chrono.start = System.currentTimeMillis() + 10000;

    }

    @Test
    void shouldLiberateBishop() {
        board = new Board("8/8/8/8/5N2/8/PPPPPPPP/6B1");
        Evaluation evaluation = new Evaluation(board, Color.white, Color.white, Constant.DEEPER);

        Node best = evaluation.getBest();

        Assertions.assertEquals("f2f3", best.move.move);
    }
}
