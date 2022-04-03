package com.codinggame.chess.board.pieces;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Move;
import com.codinggame.chess.board.Square;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    public Rook(final Square square, Color color) {
        super(square, color);
        if (color.equals(Color.black)) {
            this.notation = "r";
        } else {
            this.notation = "R";
        }
    }


    @Override
    public List<Move> legalsMove(Board board) {

        return getMovesInLine(board);
    }

    @Override
    public int getValue() {
        return 5;
    }

    @Override
    public Piece clonePiece() {
        return new Rook(this.square, this.color);
    }

    @Override
    public List<Square> getControlledSquare(Board board) {
        return new ArrayList<>();
    }
}
