package com.codinggame.chess.board.score;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Move;
import com.codinggame.chess.board.pieces.Color;

import java.util.Iterator;
import java.util.List;

public class CheckScore {
    public static int evaluate(Board board, Color color) {
        if (board.isDoACheck(color)) {

           /* //on regarde si on a un checkmate
            Color opponent = color.equals(Color.white) ? Color.black : Color.white;
            List<Move> moves = board.getMoves(opponent);
            //si pour chacun des moves on est check, alors on est mat
            boolean checkmate = true;
            Iterator<Move> moveIterator = moves.iterator();
            while(checkmate && moveIterator.hasNext()){
                Move next = moveIterator.next();
                Board boardAfterMove = board.move(next);
            }*/



            return 100;
        }
        return 0;
    }
}
