package com.codinggame.chess.evaluation;

import com.codinggame.chess.Chrono;
import com.codinggame.chess.Constant;
import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Move;
import com.codinggame.chess.board.pieces.Color;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EvaluationTest {

    Board board;

    @BeforeEach
    void setUp() {
        Chrono.start = System.currentTimeMillis() + 1000000;

    }

    @Test
    void shouldTakeQueenIsBest() {
        board = new Board("rnb1kbnr/pppppppp/8/2q5/3P4/8/PPP1PPPP/RNBQKBNR");

        Evaluation evaluation = new Evaluation(board, Color.white, Color.white, Constant.DEEPER);
        Node best = evaluation.getBest();

        assertEquals("d4c5", best.move.move);
    }

    @Test
    void shouldEvaluateWithDeep2() {
        board = new Board("8/8/2q5/3p4/2P5/2Q5/8/8");

        List<Move> moves = board.getPieces(Color.white)
                .stream()
                .flatMap(p -> p.legalsMove(board).stream())
                .collect(Collectors.toList());

        Evaluation evaluation = new Evaluation(board, Color.white, Color.white, Constant.DEEPER);
        Node best = evaluation.getBest();

        assertEquals("c4d5", best.move.move);

        //si je prend le pion je perd la dame
        best = evaluation.getBest();

        assertNotEquals("c4d5", best.move.move);
    }

    @Test
    void shouldTakeRook() {
        board = new Board("2r1n3/3P4/8/8/8/8/8/8");

        Evaluation evaluation = new Evaluation(board, Color.white, Color.white, Constant.DEEPER);

        Node best = evaluation.getBest();

        assertEquals("d7c8q", best.move.move);
    }

    @Test
    void shouldTakePawn() {
        board = new Board("r1nbbkqr/pp3p1p/1n1p4/3Pp1pB/4P3/1pP4P/P4PP1/RNN1BKQR");


        Evaluation evaluation = new Evaluation(board, Color.white, Color.white, Constant.DEEPER);

        Node best = evaluation.getBest();

        assertTrue((best.move.move.equals("a2b3") || best.move.move.equals("c1b3")));
    }

    @Test
    void shouldTakeOnCheck() {
        board = new Board("8/4k3/8/1p1p4/8/2N5/8/8");

        Evaluation evaluation = new Evaluation(board, Color.white, Color.white, Constant.DEEPER);

        Node best = evaluation.getBest();

        assertEquals("c3d5", best.move.move);

    }

    @Test
    void shouldTakePawnBug() {
        board = new Board("1qnrbbkr/pp1ppppp/1n6/2p5/8/5P2/PPPPPBPP/NQNR1BKR");
        Constant.DEBUG_EVAL = true;

        Evaluation evaluation = new Evaluation(board, Color.white, Color.white, Constant.DEEPER);

        Node best = evaluation.getBest();

        assertEquals("f2c5", best.move.move);
    }

    @Test
    void shouldTakeQueen() {
        board = new Board("8/8/3q4/8/8/3R4/8/8");

        Evaluation evaluation = new Evaluation(board, Color.white, Color.white, Constant.DEEPER);

        Node best = evaluation.getBest();

        assertEquals("d3d6", best.move.move);

    }

    @Test
    void blackShouldTake() {
        Constant.DEBUG_EVAL=true;
        board = new Board("8/8/8/3q4/8/3Q4/8/7P");

        Evaluation evaluation = new Evaluation(board, Color.black, Color.black, Constant.DEEPER);

        Node best = evaluation.getBest();

        assertEquals("d5d3", best.move.move);

    }

    @Test
    void bugWhite() {
        board = new Board("rnkb1q1n/ppppp1pr/5pb1/2P4p/8/N2PP1P1/PPBBNP1P/R1K2Q1R");


        Evaluation evaluation = new Evaluation(board, Color.white, Color.white, Constant.DEEPER);

        Node best = evaluation.getBest();

        assertNotEquals("c5c6", best.move.move);


    }

    @Test
    void bugWihte2() {
        board = new Board("8/pp6/3p3p/Pb2p1pP/4P3/3P1P2/2P5/8");


        Evaluation evaluation = new Evaluation(board, Color.white, Color.white, Constant.DEEPER);

        Node best = evaluation.getBest();

        assertEquals("c2c4", best.move.move);

    }

    @Test
    void bugMoveWhite() {
        board = new Board("rb1kbnnr/pppqpppp/8/3p4/P6P/8/1PPPPPP1/RBQKBNNR");

        Evaluation evaluation = new Evaluation(board, Color.white, Color.white, Constant.DEEPER);

        Node best = evaluation.getBest();

        assertNotEquals("g2g4", best.move.move);
    }

    @Test
    void shouldPreferCheck() {
        board = new Board("8/8/3k3P/8/8/8/8/Q7");

        Evaluation evaluation = new Evaluation(board, Color.white, Color.white, Constant.DEEPER);

        Node best = evaluation.getBest();

        assertNotEquals("h6h7", best.move.move);

    }

    @Test
    void bugWhitePawn() {
        board = new Board("1rbn1krb/p1p2ppp/1nqpp3/8/1P1P1PPP/P1P1P3/8/QRBNNKR1");

        Evaluation evaluation = new Evaluation(board, Color.white, Color.white, Constant.DEEPER);

        Node best = evaluation.getBest();


        assertNotEquals("e3e4", best.move.move);
    }

    @Test
    void bugQueenSacrificeWhite(){
        Constant.DEBUG_EVAL=true;
        board = new Board("qn2bbkr/ppp1pppp/3r4/8/1PP1pnPP/N7/P7/Q1RNBBKR");

        Evaluation evaluation = new Evaluation(board, Color.white, Color.white, Constant.DEEPER);

        Node best = evaluation.getBest();


        assertNotEquals("a1g7", best.move.move);
    }

    @Test
    void bugQueenSacrificeWhiteSimplified(){
        Constant.DEBUG_EVAL=true;
        board = new Board("6kq/6p1/8/8/8/8/P7/QK6");

        Evaluation evaluation = new Evaluation(board, Color.white, Color.white, Constant.DEEPER);

        Node best = evaluation.getBest();


        assertNotEquals("a1g7", best.move.move);
    }


    @Test
    void bugWhitePawn2() {
        board = new Board("rqknbr1b/p1pp1ppp/P3p2n/1p6/8/8/1PPPPPPP/RQKNBRNB");

        Evaluation evaluation = new Evaluation(board, Color.white, Color.white, Constant.DEEPER);

        Node best = evaluation.getBest();


        assertNotEquals("c2c4", best.move.move);

    }

    @Test
    void shouldPromoteQueen() {
        board = new Board("8/4P3/8/8/8/2r5/1P6/8");

        Evaluation evaluation = new Evaluation(board, Color.white, Color.white, Constant.DEEPER);

        Node best = evaluation.getBest();


        assertEquals("e7e8q", best.move.move);
    }

    @Test
    void shouldTakeBishop() {
        Constant.DEBUG_EVAL = true;
        Constant.DEEPER=1;
        board = new Board("nqr1k1br/pppp1pQ1/6np/8/2P5/b7/1P1PPPPP/N1RBKNBR");
        Evaluation evaluation = new Evaluation(board, Color.white, Color.white, Constant.DEEPER);

        Node best = evaluation.getBest();


        assertEquals("b2a3", best.move.move);

    }

    @Test
    void shouldCheckWithBishop() {
        Constant.DEBUG_EVAL = true;

        board = new Board("q2nbnr1/3p1p2/ppr1pk2/2p4p/P1P1P2P/1P1PNPPR/4R3/1Bb1BNK1");

        Evaluation evaluation = new Evaluation(board, Color.white, Color.white, Constant.DEEPER);

        Node best = evaluation.getBest();


        assertEquals("e1c3", best.move.move);
    }

    @Test
    void shouldTakePawn2() {

        Constant.DEBUG_EVAL = true;
        Constant.DEEPER = 2;
        board = new Board("1q3rk1/5ppp/1n6/2p5/8/8/5B1P/Q5K1");

        Evaluation evaluation = new Evaluation(board, Color.white, Color.white, Constant.DEEPER);

        Node best = evaluation.getBest();


        assertEquals("f2c5", best.move.move);
    }

    @Test
    void evaluationShouldCreateNodeForPlayer(){
        Board board = new Board("8/8/8/8/8/3P4/2P5/8");
        Evaluation evaluation = new Evaluation(board,Color.white,Color.white,Constant.DEEPER);
        assertEquals(3,evaluation.nodes.size());
    }

    @Test
    void evaluationShouldCreateNodeForOtherPlayer(){
        Board board = new Board("8/8/8/2p5/8/3P4/2P5/8");
        Evaluation evaluation = new Evaluation(board,Color.white,Color.white,Constant.DEEPER);
        assertEquals(3,evaluation.nodes.size());

        Node d3d4 = evaluation.nodes.stream().filter(n -> n.move.move.equals("d3d4")).findFirst().get();

        assertNotNull(d3d4);

        Node c5c4 = d3d4.getChilds().stream().filter(n -> n.move.move.equals("c5d4")).findFirst().get();

        assertNotNull(c5c4);

    }

    @Test
    void evaluationShouldOnlyCreateNodeForOpponentWhenTakePiece(){
        Board board = new Board("brkqnrnb/pppppppp/8/8/8/8/PPPPPPPP/BRKQNRNB");
        Evaluation evaluation = new Evaluation(board,Color.white,Color.white,Constant.DEEPER);
        assertEquals(20,evaluation.nodes.size());

        List<Node> childsNode = evaluation.nodes.stream()
                .flatMap(n -> n.getChilds().stream())
                .collect(Collectors.toList());

        assertEquals(0,childsNode.size());

    }

}