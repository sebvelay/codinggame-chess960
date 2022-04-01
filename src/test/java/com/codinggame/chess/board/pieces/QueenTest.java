package com.codinggame.chess.board.pieces;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Square;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class QueenTest {

    @Test
    void queenMoves() {

        Board board = new Board();
        board.applyFen("8/8/P3b3/4r3/2Q5/8/8/2N2p2");

        Piece queen = board.getPiece(new Square("c4"));

        List<String> moves = queen.legalsMove(board).stream().map(m -> m.move).collect(Collectors.toList());

        List<String> expectedMove = Arrays.asList("c4d4","c4e4","c4f4","c4g4","c4h4","c4d5","c4e6","c4d3","c4e2","c4f1","c4c3","c4c2","c4b4","c4a4","c4b3","c4a2","c4b5","c4c5","c4c6","c4c7","c4c8");

        Collections.sort(moves);
        Collections.sort(expectedMove);
        assertTrue(moves.containsAll(expectedMove));
        assertTrue(expectedMove.containsAll(moves));

    }
}
