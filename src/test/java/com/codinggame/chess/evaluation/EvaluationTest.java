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

class EvaluationTest {

    Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
        Chrono.start = System.currentTimeMillis() + 1000000;

    }

    @Test
    void shouldTakeQueenIsBest() {
        board.applyFen("rnb1kbnr/pppppppp/8/2q5/3P4/8/PPP1PPPP/RNBQKBNR");

        List<Move> moves = board.getPieces(Color.white)
                .stream()
                .flatMap(p -> p.legalsMove(board).stream())
                .collect(Collectors.toList());

        Evaluation evaluation = new Evaluation(board, moves, Color.white, Color.white, false);
        Node best = evaluation.getBest();

        Assertions.assertEquals("d4c5", best.move.move);
    }

    @Test
    void shouldEvaluateWithDeep2() {
        board.applyFen("8/8/2q5/3p4/2P5/2Q5/8/8");

        List<Move> moves = board.getPieces(Color.white)
                .stream()
                .flatMap(p -> p.legalsMove(board).stream())
                .collect(Collectors.toList());

        Evaluation evaluation = new Evaluation(board, moves, Color.white, Color.white, false);
        Node best = evaluation.getBest();

        Assertions.assertEquals("c4d5", best.move.move);

        //si je prend le pion je perd la dame
        evaluation.deeper(Constant.DEEPER, Color.white);
        best = evaluation.getBest();

        Assertions.assertNotEquals("c4d5", best.move.move);
    }

    @Test
    void shouldTakeRook() {
        board.applyFen("2r1n3/3P4/8/8/8/8/8/8");

        List<Move> collect = board.getPieces(Color.white)
                .stream()
                .flatMap(p -> p.legalsMove(board).stream())
                .collect(Collectors.toList());

        Evaluation evaluation = new Evaluation(board, collect, Color.white, Color.white, false);
        evaluation.deeper(Constant.DEEPER, Color.white);

        Node best = evaluation.getBest();

        Assertions.assertEquals("d7c8q", best.move.move);
    }

    @Test
    void shouldTakePawn() {
        board.applyFen("r1nbbkqr/pp3p1p/1n1p4/3Pp1pB/4P3/1pP4P/P4PP1/RNN1BKQR");

        List<Move> collect = board.getPieces(Color.white)
                .stream()
                .flatMap(p -> p.legalsMove(board).stream())
                .collect(Collectors.toList());

        Evaluation evaluation = new Evaluation(board, collect, Color.white, Color.white, false);
        evaluation.deeper(Constant.DEEPER, Color.white);

        Node best = evaluation.getBest();

        Assertions.assertTrue((best.move.move.equals("a2b3") || best.move.move.equals("c1b3")));
    }

    @Test
    void shouldTakeOnCheck() {
        board.applyFen("8/4k3/8/1p1p4/8/2N5/8/8");

        List<Move> collect = board.getPieces(Color.white)
                .stream()
                .flatMap(p -> p.legalsMove(board).stream())
                .collect(Collectors.toList());

        Evaluation evaluation = new Evaluation(board, collect, Color.white, Color.white, false);
        evaluation.deeper(Constant.DEEPER, Color.white);

        Node best = evaluation.getBest();

        Assertions.assertEquals("c3d5", best.move.move);

    }

    @Test
    void shouldTakePawnBug() {
        board.applyFen("1qnrbbkr/pp1ppppp/1n6/2p5/8/5P2/PPPPPBPP/NQNR1BKR");
        Constant.DEBUG_EVAL = true;

        List<Move> collect = board.getPieces(Color.white)
                .stream()
                .flatMap(p -> p.legalsMove(board).stream())
                .collect(Collectors.toList());

        Evaluation evaluation = new Evaluation(board, collect, Color.white, Color.white, false);
        evaluation.deeper(Constant.DEEPER, Color.white);


        Node best = evaluation.getBest();

        Assertions.assertEquals("f2c5", best.move.move);
    }

    @Test
    void shouldTakeQueen() {
        board.applyFen("8/8/3q4/8/8/3R4/8/8");

        List<Move> collect = board.getPieces(Color.white)
                .stream()
                .flatMap(p -> p.legalsMove(board).stream())
                .collect(Collectors.toList());

        Evaluation evaluation = new Evaluation(board, collect, Color.white, Color.white, false);
        evaluation.deeper(Constant.DEEPER, Color.white);

        Node best = evaluation.getBest();

        Assertions.assertEquals("d3d6", best.move.move);

    }

    @Test
    void blackShouldTake() {
        board.applyFen("8/8/8/3q4/8/3Q4/8/8");

        List<Move> collect = board.getPieces(Color.black)
                .stream()
                .flatMap(p -> p.legalsMove(board).stream())
                .collect(Collectors.toList());

        Evaluation evaluation = new Evaluation(board, collect, Color.black, Color.black, false);
        //evaluation.deeper(1);

        Node best = evaluation.getBest();

        Assertions.assertEquals("d5d3", best.move.move);

    }

    @Test
    void bugWhite() {
        board.applyFen("rnkb1q1n/ppppp1pr/5pb1/2P4p/8/N2PP1P1/PPBBNP1P/R1K2Q1R");

        List<Move> collect = board.getPieces(Color.white)
                .stream()
                .flatMap(p -> p.legalsMove(board).stream())
                .collect(Collectors.toList());

        Evaluation evaluation = new Evaluation(board, collect, Color.white, Color.white, false);
        evaluation.deeper(Constant.DEEPER, Color.white);

        Node best = evaluation.getBest();

        Assertions.assertNotEquals("c5c6", best.move.move);


    }

    @Test
    void bugWihte2() {
        board.applyFen("8/pp6/3p3p/Pb2p1pP/4P3/3P1P2/2P5/8");

        List<Move> collect = board.getPieces(Color.white)
                .stream()
                .flatMap(p -> p.legalsMove(board).stream())
                .collect(Collectors.toList());

        Evaluation evaluation = new Evaluation(board, collect, Color.white, Color.white, false);
        evaluation.deeper(Constant.DEEPER, Color.white);

        Node best = evaluation.getBest();

        Assertions.assertEquals("c2c4", best.move.move);

    }

    @Test
    void bugMoveWhite() {
        board.applyFen("rb1kbnnr/pppqpppp/8/3p4/P6P/8/1PPPPPP1/RBQKBNNR");

        List<Move> collect = board.getPieces(Color.white)
                .stream()
                .flatMap(p -> p.legalsMove(board).stream())
                .collect(Collectors.toList());

        Evaluation evaluation = new Evaluation(board, collect, Color.white, Color.white, false);
        evaluation.deeper(Constant.DEEPER, Color.white);

        Node best = evaluation.getBest();

        Assertions.assertNotEquals("g2g4", best.move.move);
    }

    @Test
    void shouldPreferCheck() {
        board.applyFen("8/8/3k3P/8/8/8/8/Q7");

        List<Move> moves = board.getMoves(Color.white);

        Evaluation evaluation = new Evaluation(board, moves, Color.white, Color.white, false);

        Node best = evaluation.getBest();

        Assertions.assertNotEquals("h6h7", best.move.move);

    }

    @Test
    void bugWhitePawn() {
        board.applyFen("1rbn1krb/p1p2ppp/1nqpp3/8/1P1P1PPP/P1P1P3/8/QRBNNKR1");
        List<Move> moves = board.getMoves(Color.white);
        Evaluation evaluation = new Evaluation(board, moves, Color.white, Color.white, false);
        evaluation.deeper(Constant.DEEPER, Color.white);

        Node best = evaluation.getBest();


        Assertions.assertNotEquals("e3e4", best.move.move);
    }

    @Test
    void bugQueenSacrificeWhite(){
        board.applyFen("qn2bbkr/ppp1pppp/3r4/8/1PP1pnPP/N7/P7/Q1RNBBKR");
        List<Move> moves = board.getMoves(Color.white);
        Evaluation evaluation = new Evaluation(board, moves, Color.white, Color.white, false);
        evaluation.deeper(Constant.DEEPER, Color.white);

        Node best = evaluation.getBest();


        Assertions.assertNotEquals("a1g7", best.move.move);
    }

    @Test
    void testOrderByTakePiece() {
        board.applyFen("qn2bbkr/ppp1pppp/3r4/8/1PP1pnPP/N7/P7/Q1RNBBKR");
        List<Move> moves = board.getMoves(Color.white);
        Evaluation evaluation = new Evaluation(board, moves, Color.white, Color.white, false);
        Assertions.assertTrue(evaluation.nodes.get(0).move.isTakePiece);
    }

    @Test
    void bugWhitePawn2() {
        board.applyFen("rqknbr1b/p1pp1ppp/P3p2n/1p6/8/8/1PPPPPPP/RQKNBRNB");
        List<Move> moves = board.getMoves(Color.white);
        Evaluation evaluation = new Evaluation(board, moves, Color.white, Color.white, false);
        evaluation.deeper(Constant.DEEPER, Color.white);

        Node best = evaluation.getBest();


        Assertions.assertNotEquals("c2c4", best.move.move);

    }

    @Test
    void shouldPromoteQueen() {
        board.applyFen("8/4P3/8/8/8/2r5/1P6/8");
        List<Move> moves = board.getMoves(Color.white);
        Evaluation evaluation = new Evaluation(board, moves, Color.white, Color.white, false);
        evaluation.deeper(Constant.DEEPER, Color.white);

        Node best = evaluation.getBest();


        Assertions.assertEquals("e7e8q", best.move.move);
    }

    @Test
    void shouldTakeBishop() {
        Constant.DEBUG_EVAL = true;
        board.applyFen("nqr1k1br/pppp1pQ1/6np/8/2P5/b7/1P1PPPPP/N1RBKNBR");
        List<Move> moves = board.getMoves(Color.white);
        Evaluation evaluation = new Evaluation(board, moves, Color.white, Color.white, false);
        evaluation.deeper(Constant.DEEPER, Color.white);

        Node best = evaluation.getBest();


        Assertions.assertEquals("b2a3", best.move.move);

    }

    @Test
    void shouldCheckWithBishop() {
        Constant.DEBUG_EVAL = true;

        board.applyFen("q2nbnr1/3p1p2/ppr1pk2/2p4p/P1P1P2P/1P1PNPPR/4R3/1Bb1BNK1");

        List<Move> moves = board.getMoves(Color.white);
        Evaluation evaluation = new Evaluation(board, moves, Color.white, Color.white, false);
        evaluation.deeper(Constant.DEEPER, Color.white);

        Node best = evaluation.getBest();


        Assertions.assertEquals("e1c3", best.move.move);
    }

    @Test
    void shouldTakePawn2() {

        Constant.DEBUG_EVAL = true;
        Constant.DEEPER = 2;
        board.applyFen("1q3rk1/5ppp/1n6/2p5/8/8/5B1P/Q5K1");

        List<Move> moves = board.getMoves(Color.white).stream().filter(m -> m.move.equals("f2c5")||m.move.equals("h2h3")).collect(Collectors.toList());
        Evaluation evaluation = new Evaluation(board, moves, Color.white, Color.white, false);
        evaluation.deeper(Constant.DEEPER, Color.white);

        Node best = evaluation.getBest();


        Assertions.assertEquals("f2c5", best.move.move);
    }

}