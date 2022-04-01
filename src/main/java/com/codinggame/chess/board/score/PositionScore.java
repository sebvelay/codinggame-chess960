package com.codinggame.chess.board.score;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Square;
import com.codinggame.chess.board.pieces.Color;

public class PositionScore {
    public static int evaluate(Board board, Color color) {
        int score = 0;
        if (color == Color.white) {
            Square d4 = board.getSquare(4, 3);
            if (board.getPiece(d4) != null && board.getPiece(d4).color == color) {
                score++;
            }

            Square e4 = board.getSquare(4, 4);
            if (board.getPiece(e4) != null && board.getPiece(e4).color == color) {
                score++;
            }
        }
        if (color == Color.black) {
            Square d5 = board.getSquare(3, 3);
            if (board.getPiece(d5) != null && board.getPiece(d5).color == color) {
                score++;
            }

            Square e5 = board.getSquare(3, 4);
            if (board.getPiece(e5) != null && board.getPiece(e5).color == color) {
                score++;
            }
        }

        score += board.getMoves(color).stream().filter(m -> m.piece.getValue() > 1).count();


        return score;
    }
}
