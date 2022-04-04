package com.codinggame.chess.evaluation;

import com.codinggame.chess.Chrono;
import com.codinggame.chess.Constant;
import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.pieces.Color;
import com.codinggame.chess.board.pieces.Piece;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QueenTakeTest {

    @BeforeEach
    void setUp() {

        Chrono.start = System.currentTimeMillis() + 10000000;

    }

    @Test
    void shouldTakePawn() {
        Constant.DEEPER = 0;
        Constant.DEBUG_EVAL = true;
        Board board = new Board("8/2p5/3r4/5p2/8/3Q4/8/8");

        Evaluation evaluation = new Evaluation(board, Color.white, Color.white, Constant.DEEPER);

        Node best = evaluation.getBest();

        assertEquals("d3f5", best.move.move);
    }

    @Test
    void shouldReturnControlledSquare() {
        Constant.DEEPER = 0;
        Board board = new Board("8/2p5/3r4/5p2/8/3Q4/8/8");

        List<Piece> pieces = board.getPieces(Color.black);
        List<String> squareControlled = pieces.stream()
                .flatMap(p -> p.getControlledSquare(board).stream())
                .map(s->s.translate)
                .collect(Collectors.toList());

        assertTrue(squareControlled.contains("d6"));
    }
}
