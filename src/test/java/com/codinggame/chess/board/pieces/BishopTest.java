package com.codinggame.chess.board.pieces;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Square;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BishopTest {

    @Test
    void shouldReturnControlledSquare() {
        Board board = new Board("8/8/8/8/8/3B4/8/8");

        Square d3Square = Square.of("d3");
        Piece d3 = board.getPiece(d3Square);
        List<String> expected = Arrays.asList("b1", "c2", "e4", "f5", "g6", "h7", "f1", "e2", "c4", "b5", "a6");

        List<String> collect = d3.getControlledSquare(board,d3Square).stream().map(s -> s.translate).collect(Collectors.toList());

        Assertions.assertTrue(collect.containsAll(expected));
        Assertions.assertTrue(expected.containsAll(collect));


    }
}
