package com.codinggame.chess.board.score;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.pieces.Color;
import com.codinggame.chess.board.pieces.Piece;

public class MaterialScore {

    public static int evaluate(Board board, Color color) {
        int score = 0;
        for (Piece p : board.getPieces(color)) {
            score += p.getValue();
        }

        return score * 1000;
    }
}
