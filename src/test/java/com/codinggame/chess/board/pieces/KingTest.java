package com.codinggame.chess.board.pieces;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Square;
import com.codinggame.chess.board.pieces.Piece;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {

    @Test
    void shouldMove(){
        Board board=new Board("8/8/1n6/K7/N7/8/8/8");

        Piece king = board.getPiece(Square.of("a5"));

        List<String> moves = king.legalsMove(board,Square.of("a5")).stream().map(m->m.move).collect(Collectors.toList());

        List<String> expected = Arrays.asList("a5a6","a5b6","a5b5","a5b4");

        assertTrue(moves.containsAll(expected));
        assertTrue(expected.containsAll(moves));
    }

    @Test
    void cantMoveIfImCheck(){
        Board board = new Board("k7/3Q4/8/8/8/8/8/8");
        Piece king = board.getPiece(Square.of("a8"));
        List<String> moves = king.legalsMove(board,Square.of("a8")).stream().map(m->m.move).collect(Collectors.toList());
        List<String> expected = Arrays.asList("a8b8");
        assertTrue(moves.containsAll(expected));
        assertTrue(expected.containsAll(moves));
    }

    @Test
    void kingCantTakeIfControlledByOpponent(){
        Board board = new Board("k1r5/1Rp3pp/Q3pn2/p1b2p2/8/4P2P/5qPB/K7");
        Piece king = board.getPiece(Square.of("a8"));
        List<String> moves = king.legalsMove(board,Square.of("a8")).stream().map(m->m.move).collect(Collectors.toList());
        assertEquals(0,moves.size());
    }

    @Test
    void notSucideOnKing(){
        Board board = new Board("1r1kb2r/p1bppppp/1pp5/6Q1/q2nn2P/8/BPP1PPP1/R2KBNNR");
        Piece king = board.getPiece(Square.of("d8"));

        List<String> collect = king.getControlledSquare(board,Square.of("d8")).stream().map(s -> s.translate).collect(Collectors.toList());

        assertTrue(collect.contains("e7"));

    }
}
