package com.codinggame.chess.board.score;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.pieces.Color;

public class PositionScore {
    public static int evaluate(Board board, Color color) {
        int score = 0;

        score += board.getMoves(color).stream().filter(m -> m.piece.getValue() > 1).count();

        return score;
    }
}
