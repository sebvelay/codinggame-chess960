package com.codinggame.chess.board.pieces;

import com.codinggame.chess.board.Board;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PawnTest {

    @Test
    void shouldHave3Moves(){

        Board board = new Board("2r1n3/3P4/8/8/8/8/8/8");

        List<String> collect = board.getPieces(Color.white)
                .stream()
                .flatMap(p -> p.legalsMove(board).stream())
                .map(m->m.move)
                .collect(Collectors.toList());

        List<String> expected = Arrays.asList("d7d8q","d7c8q","d7e8q");

        Assertions.assertTrue(collect.containsAll(expected));

    }

}
