package com.codinggame.chess.board;

import com.codinggame.chess.Cache;
import com.codinggame.chess.board.pieces.Color;
import com.codinggame.chess.board.pieces.Pawn;
import com.codinggame.chess.board.pieces.Piece;
import com.codinggame.chess.board.pieces.Rook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    @Test
    void shouldReturnPiece(){
        Board board = new Board("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");

        assertTrue(board.getPiece(new Square(0,0)) instanceof Rook);
    }

    @Test
    void shouldReturnPieceNull(){
        Board board = new Board("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");

        assertNull(board.getPiece(new Square(4,0)));
    }

    @Test
    void shouldDeterminePosWithEmptyPos(){
        Board board = new Board("rnbqkbnr/pppppppp/8/8/3P4/8/PPP1PPPP/RNBQKBNR");

        assertNull(board.getPiece(new Square(4,1)));
        assertTrue(board.getPiece(new Square(4,3)) instanceof Pawn);

    }

    @Test
    void shouldDeterminePosWithDoubleEmptyPos(){
        Board board = new Board("rnbqkbnr/pppppppp/8/8/3P2P1/8/PPP1PP1P/RNBQKBNR");

        assertTrue(board.getPiece(new Square(4,3)) instanceof  Pawn);
        assertTrue(board.getPiece(new Square(4,6)) instanceof  Pawn);

    }

    @Test
    void shouldDeterminePosWithDoubleEmptyPosAndPawnAtStart(){
        Board board = new Board("rnbqkbnr/pppppppp/8/8/P2P2P1/8/1PP1PP1P/RNBQKBNR");

        assertTrue(board.getPiece(new Square(4,0)) instanceof  Pawn);
        assertTrue(board.getPiece(new Square(4,3)) instanceof  Pawn);
        assertTrue(board.getPiece(new Square(4,6)) instanceof  Pawn);

    }

    @Test
    void shouldTakePiece(){
        Board board = new Board("8/8/2q5/3p4/2P5/2Q5/8/8");

        Piece whitePawn = board.getPiece(new Square("c4"));
        Piece blackPawn = board.getPiece(new Square("d5"));

        Move move = new Move(true,whitePawn,new Square("d5"),blackPawn,false);

        Board newBoard = board.takePiece(move);

        assertNull(newBoard.getPiece(new Square("c4")));
        assertTrue(newBoard.getPiece(new Square("d5")).color.equals(Color.white));
    }

    @Test
    void isCheckBlack(){
        Board board = new Board("8/8/2k5/8/8/2Q5/8/8");

        boolean check = board.isCheck(Color.black);

        assertTrue(check);
    }

    @Test
    void isNotCheckBlack(){
        Board board = new Board("8/8/2k5/8/3Q4/8/8/8");

        boolean check = board.isCheck(Color.black);

        assertFalse(check);
    }

    @Test
    void blackNotCheck() {

        Board board = new Board("1k2P3/R3P3/2PRP3/3P4/8/8/8/8");

        boolean check = board.isCheck(Color.black);

        assertFalse(check);

    }


    @Test
    void getFen() {
        Board board = new Board("1k2P3/R3P3/2PRP3/3P4/8/8/8/8");

        String fen = board.getFen();

        assertEquals("1k2P3/R3P3/2PRP3/3P4/8/8/8/8", fen);
    }


    @Test
    void shouldReturnScoreForBoard() {
        Board board = new Board("Q7/8/8/8/8/8/8/8");

        int score = board.getScore();

        assertEquals(9021, score);

    }

    @Test
    void shouldCreateBoardWithFen() {
        Board board = new Board("8/8/8/3P4/8/8/8/8");

        Piece d5 = board.getPiece(new Square("d5"));

        assertTrue(d5 instanceof Pawn);
    }

    @Test
    void newBoardShouldBeOnCache() {
        Board board = new Board("8/8/8/3P4/8/8/8/8");

        Board boardCached = Cache.cachedBoard.get("8/8/8/3P4/8/8/8/8");

        assertEquals(board,boardCached);
    }

    @Test
    void moveShouldGenerateNewBoad(){
        Board board = new Board("8/8/8/3P4/8/8/8/8");
        Move d5d6 = board.getMoves(Color.white).stream().filter(m -> m.move.equals("d5d6")).findFirst().get();
        Board newBoardAfterMove = board.move(d5d6);

        assertEquals("8/8/3P4/8/8/8/8/8",newBoardAfterMove.getFen());

        assertEquals(Cache.cachedBoard.get("8/8/8/3P4/8/8/8/8"),board);
        assertEquals(Cache.cachedBoard.get("8/8/3P4/8/8/8/8/8"),newBoardAfterMove);
    }

    @Test
    void takePieceShouldGenerateNewBoad(){
        Board board = new Board("8/8/4p3/3P4/8/8/8/8");
        Move d5e6 = board.getMoves(Color.white).stream().filter(m -> m.move.equals("d5e6")).findFirst().get();
        Board newBoardAfterTakePiece = board.takePiece(d5e6);

        assertEquals("8/8/4P3/8/8/8/8/8",newBoardAfterTakePiece.getFen());

        assertEquals(Cache.cachedBoard.get("8/8/4p3/3P4/8/8/8/8"),board);
        assertEquals(Cache.cachedBoard.get("8/8/4P3/8/8/8/8/8"),newBoardAfterTakePiece);
    }
}
