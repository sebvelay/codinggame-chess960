package com.codinggame.chess.evaluation;

import com.codinggame.chess.Chrono;
import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Move;
import com.codinggame.chess.board.pieces.Color;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EvaluationNodeBestTest {

    Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
        Chrono.start=System.currentTimeMillis()+1000000;

    }

    @Test
    void shouldEvaluateNodes() {
        board.applyFen("8/8/6b1/8/8/8/3P4/8");
        List<Move> moves = board.getPieces(Color.white)
                .stream()
                .flatMap(p -> p.legalsMove(board).stream())
                .collect(Collectors.toList());

        Evaluation evaluation = new Evaluation(board, moves, Color.white,Color.white,false);

        Assertions.assertEquals(2, evaluation.nodes.size());
    }

    @Test
    void shouldEvaluateBest(){
        board.applyFen("8/8/6b1/8/8/8/3P4/8");
        List<Move> moves = board.getPieces(Color.white)
                .stream()
                .flatMap(p -> p.legalsMove(board).stream())
                .collect(Collectors.toList());

        Evaluation evaluation = new Evaluation(board, moves, Color.white,Color.white,false);
        evaluation.deeper(2,Color.white);

        Node best = evaluation.getBest();

        Assertions.assertEquals("d2d4",best.move.move);
    }

    @Test
    void shouldEvaluateBestTakeQueen(){
        board.applyFen("qrnkrbbn/pPppp3/8/6p1/6P1/5p1p/1PPPPP1P/QRK1RBBN");
        List<Move> moves = board.getPieces(Color.white)
                .stream()
                .flatMap(p -> p.legalsMove(board).stream())
                .collect(Collectors.toList());

        Evaluation evaluation = new Evaluation(board, moves, Color.white,Color.white,false);
        evaluation.deeper(1,Color.white);

        Node best = evaluation.getBest();

        Assertions.assertEquals("b7a8q",best.move.move);
    }

}
