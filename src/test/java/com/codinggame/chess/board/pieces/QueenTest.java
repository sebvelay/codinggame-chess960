package com.codinggame.chess.board.pieces;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Square;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QueenTest {

    @Test
    void queenMoves() {

        Board board = new Board("8/8/P3b3/4r3/2Q5/8/8/2N2p2");

        Piece queen = board.getPiece(Square.of("c4"));

        List<String> moves = queen.legalsMove(board,Square.of("c4")).stream().map(m -> m.move).collect(Collectors.toList());

        List<String> expectedMove = Arrays.asList("c4d4", "c4e4", "c4f4", "c4g4", "c4h4", "c4d5", "c4e6", "c4d3", "c4e2", "c4f1", "c4c3", "c4c2", "c4b4", "c4a4", "c4b3", "c4a2", "c4b5", "c4c5", "c4c6", "c4c7", "c4c8");

        Collections.sort(moves);
        Collections.sort(expectedMove);
        assertTrue(moves.containsAll(expectedMove));
        assertTrue(expectedMove.containsAll(moves));
    }

    @Test
    void queenControleSquare() {

        Board board = new Board("k7/3Q4/8/8/8/8/8/8");
        Piece queen = board.getPiece(Square.of("d7"));

        List<Square> controlledSquare = queen.getControlledSquare(board,Square.of("d7"));


        assertEquals(23, controlledSquare.size());
    }

    @Test
    void whiteQueenControleSquareWhereRookOnHere(){
        Board board = new Board("k1r5/1Rp3pp/Q3pn2/p1b2p2/8/4P2P/5qPB/K7");
        Piece queen = board.getPiece(Square.of("a6"));
        List<String> controlledSquare = queen.getControlledSquare(board,Square.of("a6")).stream().map(s->s.translate).collect(Collectors.toList());

        assertTrue(controlledSquare.contains("b7"));
    }

    @Test
    void whiteQueenControleSquareWhereBishopOnHere(){
        Board board = new Board("k1r5/1Rp3pp/QB2pn2/p1b2p2/8/4P2P/5qPB/K7");
        Piece queen = board.getPiece(Square.of("a6"));
        List<String> controlledSquare = queen.getControlledSquare(board,Square.of("a6")).stream().map(s->s.translate).collect(Collectors.toList());

        assertTrue(controlledSquare.contains("b6"));
    }



}
