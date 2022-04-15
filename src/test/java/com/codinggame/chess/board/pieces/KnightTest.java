package com.codinggame.chess.board.pieces;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Square;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class KnightTest {

    @Test
    void shouldMove() {
        Board board = new Board("8/8/8/8/3N4/8/8/8");

        Piece piece = board.getPiece(Square.of("d4"));

        List<String> moves = piece.legalsMove(board, Square.of("d4")).stream().map(m -> m.move).collect(Collectors.toList());

        List<String> expected = Arrays.asList("d4b5", "d4c6", "d4e6", "d4f5", "d4f3", "d4e2", "d4c2", "d4b3");

        Assertions.assertTrue(moves.containsAll(expected));
        Assertions.assertTrue(expected.containsAll(moves));
    }

    @Test
    void shouldGetControlledSquare() {
        Board board = new Board("8/8/4p3/8/3N4/8/4P3/8");

        Piece piece = board.getPiece(Square.of("d4"));
        List<String> expected = Arrays.asList("b5", "c6", "e6", "f5", "f3", "e2", "c2", "b3");

        List<String> res = piece.getControlledSquare(board, Square.of("d4")).stream().map(s -> s.translate).collect(Collectors.toList());

        Assertions.assertTrue(res.containsAll(expected));
        Assertions.assertTrue(expected.containsAll(res));

    }
}
