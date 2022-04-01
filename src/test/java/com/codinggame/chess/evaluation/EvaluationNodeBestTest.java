package com.codinggame.chess.evaluation;

import com.codinggame.chess.Chrono;
import com.codinggame.chess.Constant;
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
        Chrono.start=System.currentTimeMillis()+1000000;

    }

    @Test
    void shouldEvaluateNodes() {
        board = new Board("8/8/6b1/8/8/8/3P4/8");

        Evaluation evaluation = new Evaluation(board, Color.white,Color.white, Constant.DEEPER);

        Assertions.assertEquals(2, evaluation.nodes.size());
    }

    @Test
    void shouldEvaluateBest(){
        Constant.DEBUG_EVAL=true;
        board = new Board("8/8/6b1/8/8/8/3P4/8");

        Evaluation evaluation = new Evaluation(board, Color.white,Color.white, Constant.DEEPER);

        Node best = evaluation.getBest();

        Assertions.assertEquals("d2d4",best.move.move);
    }

    @Test
    void shouldEvaluateBestTakeQueen() {

        board = new Board("qrnkrbbn/pPppp3/8/6p1/6P1/5p1p/1PPPPP1P/QRK1RBBN");


        Evaluation evaluation = new Evaluation(board, Color.white, Color.white, Constant.DEEPER);

        Node best = evaluation.getBest();

        Assertions.assertEquals("b7a8q",best.move.move);
    }

}
