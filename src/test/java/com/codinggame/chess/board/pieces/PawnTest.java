package com.codinggame.chess.board.pieces;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Square;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PawnTest {

    @Test
    void shouldHave3Moves() {

        Board board = new Board("2r1n3/3P4/8/8/8/8/8/8");

        List<String> collect = board.getMoves(Color.white).stream()
                .map(m -> m.move)
                .collect(Collectors.toList());

        List<String> expected = Arrays.asList("d7d8q", "d7c8q", "d7e8q");

        assertTrue(collect.containsAll(expected));

    }

    /*@Test
    void shouldReturnControlledSquareForBlack() {
        Board board = new Board("8/2p5/8/8/8/8/8/8");
        Piece c7 = board.getPiece(Square.of("c7"));
        List<String> controlledSquare = c7.getControlledSquare(board)
                .stream()
                .map(s -> s.translate)
                .collect(Collectors.toList());
        assertTrue(controlledSquare.containsAll(Arrays.asList("b6", "d6")));
        assertEquals(2, controlledSquare.size());
    }*/

    /*@Test
    void shouldReturnControlledSquareForWhite() {
        Board board = new Board("8/8/8/8/4P3/8/8/8");
        Piece e4 = board.getPiece(Square.of("e4"));
        List<String> controlledSquare = e4.getControlledSquare(board)
                .stream()
                .map(s -> s.translate)
                .collect(Collectors.toList());
        assertTrue(controlledSquare.containsAll(Arrays.asList("f5", "d5")));
        assertEquals(2, controlledSquare.size());

    }*/

}
