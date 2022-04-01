package com.codinggame.chess.evaluation;

import com.codinggame.chess.Constant;
import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Move;
import com.codinggame.chess.board.pieces.Color;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CheckMateTest {


    @Test
    void checkMate(){
        Board board = new Board("k3P3/2R1P3/2PRP3/3P4/8/8/8/8");


        Evaluation evaluation = new Evaluation(board, Color.white,Color.white, Constant.DEEPER);

        Node best = evaluation.getBest();

        Assertions.assertEquals("d6d8", best.move.move);
    }

    @Test
    void notACheckMate(){
        Board board = new Board("n1rkr1bn/pp1ppp1p/2p3p1/8/3q4/6P1/PPPPQ1PP/1BKRR1B1");

        Evaluation evaluation = new Evaluation(board, Color.black,Color.black, Constant.DEEPER);

        Node best = evaluation.getBest();

        Assertions.assertNotEquals("d4b2", best.move.move);
    }
}
