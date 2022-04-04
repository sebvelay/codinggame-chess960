package com.codinggame.chess.board.score;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.pieces.Color;

public class PositionScore {
    public static int evaluate(Board board, Color color) {
        //return 0;
        int score = 0;
        score += board.getMoves(color).size();



        return score;
    }
}
