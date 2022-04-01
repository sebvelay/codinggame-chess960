package com.codinggame.chess.board.pieces;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Square;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class KnightTest {

    @Test
    void shouldMove() {
        Board board = new Board();
        board.applyFen("8/8/8/8/3N4/8/8/8");

        Piece piece = board.getPiece(new Square("d4"));

        List<String> moves = piece.legalsMove(board).stream().map(m -> m.move).collect(Collectors.toList());

        List<String> expected = Arrays.asList("d4b5", "d4c6", "d4e6", "d4f5", "d4f3", "d4e2", "d4c2", "d4b3");

        Assertions.assertTrue(moves.containsAll(expected));
        Assertions.assertTrue(expected.containsAll(moves));
    }
}
