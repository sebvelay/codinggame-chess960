package com.codinggame.chess.board.score;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Move;
import com.codinggame.chess.board.pieces.Color;

public class PositionScore {
    public static int evaluate(Board board, Color color) {
        int score = 0;

        for(Move m : board.getMoves(color)){
            if(m.piece.getValue()>1){
                score++;
            }
        }

        return score;
    }
}
