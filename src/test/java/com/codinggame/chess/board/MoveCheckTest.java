package com.codinggame.chess.board;

import com.codinggame.chess.board.pieces.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class MoveCheckTest {

    @Test
    void cantMoveIfStillCheck(){
        Board board = new Board("5r1n/1p1Npk2/p1n2p2/5Pp1/7p/3P2P1/P2K2q1/2R4N");
        List<Move> moves = board.getMoves(Color.white);
        List<String> movesString = moves.stream().map(m -> m.move).collect(Collectors.toList());
        Assertions.assertFalse(movesString.contains("d2e2"));

    }
}
