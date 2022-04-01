package com.codinggame.chess.evaluation;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Move;
import com.codinggame.chess.board.pieces.Color;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CheckMateTest {

    /*@Test
    void shouldGetCheckMove(){

        Board board = new Board();
        board.applyFen("k7/2R5/3R2b1/8/8/8/8/2K5");

        List<Move> collect = board.getPieces(Color.white)
                .stream()
                .flatMap(p -> p.legalsMove(board).stream())
                .collect(Collectors.toList());

        Evaluation evaluation = new Evaluation(board, collect, Color.white,Color.white,true);

        List<String> result = evaluation.nodes.stream().filter(m -> m.move.isCheck).map(m -> m.move.move).collect(Collectors.toList());

        List<String> expected = Arrays.asList("c7c8","c7a7","d6a6","d6d8");

        Assertions.assertTrue(result.containsAll(expected));
        Assertions.assertTrue(expected.containsAll(result));
    }*/


    @Test
    void checkMate(){
        Board board = new Board("k3P3/2R1P3/2PRP3/3P4/8/8/8/8");

        List<Move> collect = board.getMoves(Color.white);

        Evaluation evaluation = new Evaluation(board, collect, Color.white,Color.white,true);

        Node best = evaluation.getBest();

        Assertions.assertEquals("d6d8", best.move.move);
    }

    @Test
    void notACheckMate(){
        Board board = new Board("n1rkr1bn/pp1ppp1p/2p3p1/8/3q4/6P1/PPPPQ1PP/1BKRR1B1");

        List<Move> collect = board.getPieces(Color.black)
                .stream()
                .flatMap(p -> p.legalsMove(board).stream())
                .collect(Collectors.toList());

        Evaluation evaluation = new Evaluation(board, collect, Color.black,Color.black,true);
        evaluation.deeper(1, Color.black);

        Node best = evaluation.getBest();

        Assertions.assertNotEquals("d4b2", best.move.move);
    }
}
