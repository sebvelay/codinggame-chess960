package com.codinggame.chess.board.pieces;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Move;
import com.codinggame.chess.board.Square;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RookTest {

    @Test
    void shouldMove(){

        Board board = new Board("8/pR3B2/8/8/1n6/8/8/8");

        Piece rook = board.getPiece(Square.of("b7"));

        List<Move> moveList = rook.legalsMove(board);

        List<String> collect = moveList.stream().map(m -> m.target)
                .map(s -> s.translate)
                .collect(Collectors.toList());

        List<String> expected = Arrays.asList("a7","c7","d7","e7","b8","b6","b5","b4");

        Assertions.assertTrue(collect.containsAll(expected));

    }

    @Test
    void shouldMove2(){
        Board board = new Board("8/8/8/8/2R5/8/8/8");

        Piece rook = board.getPiece(Square.of("c4"));

        List<Move> moveList = rook.legalsMove(board);


        List<String> collect = moveList.stream().map(m -> m.target)
                .map(s -> s.translate)
                .collect(Collectors.toList());

        List<String> expected = Arrays.asList("a4","b4","d4","e4","f4","g4","h4","c8","c7","c6","c5","c3","c2","c1");

        Assertions.assertTrue(collect.containsAll(expected));

    }

    @Test
    void rookControleSquare() {

        Board board = new Board("k2RP3/2R1P3/2P1P3/3P4/8/8/8/8");
        Piece rook = board.getPiece(Square.of("c7"));

        List<Square> controlledSquare = rook.getControlledSquare(board);


        assertEquals(4, controlledSquare.size());
    }
}
