package com.codinggame.chess.board.score;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.pieces.Color;

public class CheckScore {
    public static int evaluate(Board board, Color color){
        if(board.isDoACheck(color)){
            return 100;
        }
        return 0;
    }
}
