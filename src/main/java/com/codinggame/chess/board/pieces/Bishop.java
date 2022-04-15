package com.codinggame.chess.board.pieces;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Move;
import com.codinggame.chess.board.Square;

import java.util.List;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
        if (color.equals(Color.black)) {
            this.notation = "b";
        } else {
            this.notation = "B";
        }
    }

    @Override
    public List<Square> getControlledSquare(Board board, Square from) {
        return getSquareInDiagognale(board, from);
    }

    @Override
    public List<Move> legalsMove(Board board, Square from) {
        return getMovesInDiagognale(board, from);


    }

    @Override
    public int getValue() {
        return 3;
    }
}
