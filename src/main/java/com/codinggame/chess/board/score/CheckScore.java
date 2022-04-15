package com.codinggame.chess.board.score;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Move;
import com.codinggame.chess.board.pieces.Color;

import java.util.List;

public class CheckScore {
    public static int evaluate(Board board, Color color) {
        if (board.isDoACheck(color)) {

            //on regarde si on a un checkmate
            Color opponent = color.equals(Color.white) ? Color.black : Color.white;
            List<Move> moves = board.getMoves(opponent);
            //TODO si on a pas de moves, alors on est pat
            //si on est toujours check dans le nouveau board et qu'on a pas de move on est mat
            if (moves.size() == 0) {
                return 10000000;
            }


            return 100;
        }
        return 0;
    }
}
