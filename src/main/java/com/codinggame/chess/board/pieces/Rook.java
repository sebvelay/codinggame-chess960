package com.codinggame.chess.board.pieces;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Move;
import com.codinggame.chess.board.Square;

import java.util.List;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color);
        if (color.equals(Color.black)) {
            this.notation = "r";
        } else {
            this.notation = "R";
        }
    }


    @Override
    public List<Move> legalsMove(Board board, Square from) {

        return getMovesInLine(board, from);
    }

    @Override
    public int getValue() {
        return 5;
    }

    @Override
    public List<Square> getControlledSquare(Board board, Square from) {
        return getSquaresInLine(board, from);
    }
}
