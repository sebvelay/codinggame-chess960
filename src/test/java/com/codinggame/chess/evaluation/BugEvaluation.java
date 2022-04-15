package com.codinggame.chess.evaluation;

import com.codinggame.chess.Chrono;
import com.codinggame.chess.Constant;
import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Square;
import com.codinggame.chess.board.pieces.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class BugEvaluation {

    @BeforeEach
    void setUp() {
        Constant.DEEPER=3;
        Constant.DEBUG_EVAL=true;
        Chrono.start = System.currentTimeMillis() + 10000;

    }

    @Test
    void bugEvaluation1(){
        Constant.DEEPER=0;
        Constant.DEBUG_EVAL=true;
        Board board = new Board("r1kbqrb1/pppp1ppp/1n2p1n1/6Q1/5P2/2P5/PP1PP1PP/NRKB1RBN");
        Evaluation evaluation = new Evaluation(board, Color.white,Color.white,Constant.DEEPER);
        Node best = evaluation.getBest();
        assertNotEquals("g5f6",best.move.move);
    }

    @Test
    @DisplayName("should take pawn")
    void bugEvaluation2(){
        Constant.DEEPER=2;
        Constant.DEBUG_EVAL=true;
        Board board = new Board("b1rnqbkr/pppp1ppp/7n/1P6/2R1p2Q/5P2/P1PPP1PP/B1NN1BKR");
        Evaluation evaluation = new Evaluation(board, Color.white,Color.white,Constant.DEEPER);
        Node best = evaluation.getBest();
        assertEquals(Square.of("e4"),best.move.target);

    }

    @Test
    void bugEvaluation3(){
        Constant.DEEPER=5;
        Constant.DEBUG_EVAL=true;
        Board board = new Board("nr1k1qr1/p1pppp2/b4n2/2P3p1/3P1b2/7R/1BN1PP1p/1R1KNQ1B");
        Evaluation evaluation = new Evaluation(board, Color.white,Color.white,Constant.DEEPER);
        Node best = evaluation.getBest();
        System.err.println(best.move.move);
        assertNotEquals("d4d5",best.move.move);
    }

    @Test
    void bugEvaluation4(){
        Board board = new Board("nrkn3Q/p1pp1b2/4pP2/4P3/8/8/qNB2P1P/NRK1B2R");
        Evaluation evaluation = new Evaluation(board, Color.white,Color.white,Constant.DEEPER);
        Node best = evaluation.getBest();
        System.err.println(best.move.move);
        assertNotEquals("h8d8",best.move.move);
    }


    
}
