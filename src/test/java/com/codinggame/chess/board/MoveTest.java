package com.codinggame.chess.board;

import com.codinggame.chess.board.pieces.Piece;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MoveTest {

    @Test
    void isSafeMoveIsKingCanTakeButAlsoControlledByMe(){
        Board board = new Board("bqn2r1r/ppp1nk2/5p2/4Q2p/4P3/2P2R2/PP4PP/B1NB1KNR");
        Piece rook = board.getPiece(Square.of("f3"));
        Piece pawn = board.getPiece(Square.of("f6"));
        Move move = new Move(true,rook,Square.of("f6"),pawn,false);
        move.setSimulateBoard(board);

        boolean safeMove = move.isSafeMove();

        assertTrue(safeMove);
    }

    @Test
    void isSafeMoveIfCantakeMeBack(){
        Board board = new Board("8/8/8/2p5/8/3R4/8/8");
        Piece rook = board.getPiece(Square.of("d3"));
        Move move = new Move(rook,Square.of("d5"),false);
        move.setSimulateBoard(board);

        boolean safeMove = move.isSafeMove();

        assertTrue(safeMove);
    }

    @Test
    void isNotSafeICanBeTake(){
        Board board = new Board("8/8/8/2p5/8/3R4/8/8");
        Piece rook = board.getPiece(Square.of("d3"));
        Move move = new Move(rook,Square.of("d4"),false);
        move.setSimulateBoard(board);

        boolean safeMove = move.isSafeMove();

        assertFalse(safeMove);
    }

    @Test
    void isNotSafeCauseOfKing(){
        Board board = new Board("8/3k4/3r4/8/8/3Q4/8/8");
        Piece queen = board.getPiece(Square.of("d3"));
        Piece rook = board.getPiece(Square.of("d6"));

        Move move = new Move(true,queen,Square.of("d6"),rook,false);
        move.setSimulateBoard(board);

        boolean safeMove = move.isSafeMove();

        assertFalse(safeMove);
    }
}
