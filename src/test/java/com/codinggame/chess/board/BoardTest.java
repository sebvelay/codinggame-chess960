package com.codinggame.chess.board;

import com.codinggame.chess.board.pieces.Color;
import com.codinggame.chess.board.pieces.Pawn;
import com.codinggame.chess.board.pieces.Piece;
import com.codinggame.chess.board.pieces.Rook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardTest {

    @Test
    void shouldReturnPiece(){
        Board board = new Board();
        board.applyFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");

        Assertions.assertTrue(board.getPiece(new Square(0,0)) instanceof Rook);
    }

    @Test
    void shouldReturnPieceNull(){
        Board board = new Board();
        board.applyFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");

        Assertions.assertNull(board.getPiece(new Square(4,0)));
    }

    @Test
    void shouldDeterminePosWithEmptyPos(){
        Board board = new Board();
        board.applyFen("rnbqkbnr/pppppppp/8/8/3P4/8/PPP1PPPP/RNBQKBNR");

        Assertions.assertNull(board.getPiece(new Square(4,1)));
        Assertions.assertTrue(board.getPiece(new Square(4,3)) instanceof Pawn);

    }

    @Test
    void shouldDeterminePosWithDoubleEmptyPos(){
        Board board = new Board();
        board.applyFen("rnbqkbnr/pppppppp/8/8/3P2P1/8/PPP1PP1P/RNBQKBNR");

        Assertions.assertTrue(board.getPiece(new Square(4,3)) instanceof  Pawn);
        Assertions.assertTrue(board.getPiece(new Square(4,6)) instanceof  Pawn);

    }

    @Test
    void shouldDeterminePosWithDoubleEmptyPosAndPawnAtStart(){
        Board board = new Board();
        board.applyFen("rnbqkbnr/pppppppp/8/8/P2P2P1/8/1PP1PP1P/RNBQKBNR");

        Assertions.assertTrue(board.getPiece(new Square(4,0)) instanceof  Pawn);
        Assertions.assertTrue(board.getPiece(new Square(4,3)) instanceof  Pawn);
        Assertions.assertTrue(board.getPiece(new Square(4,6)) instanceof  Pawn);

    }

    @Test
    void shouldTakePiece(){
        Board board = new Board();
        board.applyFen("8/8/2q5/3p4/2P5/2Q5/8/8");

        Piece whitePawn = board.getPiece(new Square("c4"));
        Piece blackPawn = board.getPiece(new Square("d5"));

        Move move = new Move(true,whitePawn,new Square("d5"),blackPawn,false);

        board.takePiece(move);

        Assertions.assertNull(board.getPiece(new Square("c4")));
        Assertions.assertTrue(board.getPiece(new Square("d5")).color.equals(Color.white));
    }

    @Test
    void isCheckBlack(){
        Board board = new Board();
        board.applyFen("8/8/2k5/8/8/2Q5/8/8");

        boolean check = board.isCheck(Color.black);

        Assertions.assertTrue(check);
    }

    @Test
    void isNotCheckBlack(){
        Board board = new Board();
        board.applyFen("8/8/2k5/8/3Q4/8/8/8");

        boolean check = board.isCheck(Color.black);

        Assertions.assertFalse(check);
    }

    @Test
    void blackNotCheck() {

        Board board = new Board();
        board.applyFen("1k2P3/R3P3/2PRP3/3P4/8/8/8/8");

        boolean check = board.isCheck(Color.black);

        Assertions.assertFalse(check);

    }


    @Test
    void getFen() {
        Board board = new Board();
        board.applyFen("1k2P3/R3P3/2PRP3/3P4/8/8/8/8");

        String fen = board.getFen();

        Assertions.assertEquals("1k2P3/R3P3/2PRP3/3P4/8/8/8/8", fen);
    }

    @Test
    void getFenAfterClone() {
        Board board = new Board();
        board.applyFen("1k2P3/R3P3/2PRP3/3P4/8/8/8/8");
        Board clone = board.clone();

        String fen = clone.getFen();

        Assertions.assertEquals("1k2P3/R3P3/2PRP3/3P4/8/8/8/8", fen);
    }

    @Test
    void updateFenAfterMove() {
        Board board = new Board();
        board.applyFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
        Board clone = board.clone();

        Piece piece = clone.getPiece(new Square(6, 4));
        Move move = new Move(piece, new Square(4, 4), false);
        clone.move(move);

        String fen = clone.getFen();

        Assertions.assertEquals("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR", fen);
    }

    @Test
    void shouldReturnScoreForBoard() {
        Board board = new Board();
        board.applyFen("Q7/8/8/8/8/8/8/8");

        int score = board.getScore();

        Assertions.assertEquals(9021, score);

    }
}
