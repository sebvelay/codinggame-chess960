package com.codinggame.chess.board.pieces;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Square;
import com.codinggame.chess.board.pieces.Piece;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class KingTest {

    @Test
    void shouldMove(){
        Board board=new Board();
        board.applyFen("8/8/1n6/K7/N7/8/8/8");

        Piece king = board.getPiece(new Square("a5"));

        List<String> moves = king.legalsMove(board).stream().map(m->m.move).collect(Collectors.toList());

        List<String> expected = Arrays.asList("a5a6","a5b6","a5b5","a5b4");

        Assertions.assertTrue(moves.containsAll(expected));
        Assertions.assertTrue(expected.containsAll(moves));
    }
}
