package com.codinggame.chess.evaluation;

import com.codinggame.chess.Chrono;
import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Move;
import com.codinggame.chess.board.pieces.Color;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TakeCenterTest {

    Board board;

    @BeforeEach
    void setUp() {
        Chrono.start = System.currentTimeMillis() + 10000;

    }

    @Nested
    class White {
        @Test
        void shouldPreferTakeCenter() {
            board = new Board("8/8/8/4P3/8/8/PPPP1PPP/8");
            List<Move> moves = board.getMoves(Color.white);
            Evaluation evaluation = new Evaluation(board, moves, Color.white, Color.white, false);
            evaluation.deeper(3, Color.white);

            Node best = evaluation.getBest();

            Assertions.assertEquals("d2d4", best.move.move);
        }

        @Test
        void shouldPreferTakeCenter2() {
            board = new Board("8/8/8/8/8/8/PPP1PPPP/8");
            List<Move> moves = board.getMoves(Color.white);
            Evaluation evaluation = new Evaluation(board, moves, Color.white, Color.white, false);
            evaluation.deeper(3, Color.white);

            Node best = evaluation.getBest();

            Assertions.assertEquals("e2e4", best.move.move);
        }
    }

    @Nested
    class Black {
        @Test
        void shouldPreferTakeCenter() {
            board = new Board("8/pppp1ppp/8/8/8/8/8/8");
            List<Move> moves = board.getMoves(Color.black);
            Evaluation evaluation = new Evaluation(board, moves, Color.black, Color.black, false);
            evaluation.deeper(1, Color.black);

            Node best = evaluation.getBest();

            Assertions.assertEquals("d7d5", best.move.move);
        }

        @Test
        void shouldPreferTakeCenter2() {
            board = new Board("8/ppp1pppp/8/8/8/8/8/8");
            List<Move> moves = board.getMoves(Color.black);
            Evaluation evaluation = new Evaluation(board, moves, Color.black, Color.black, false);
            evaluation.deeper(1, Color.black);

            Node best = evaluation.getBest();

            Assertions.assertEquals("e7e5", best.move.move);
        }
    }

}
