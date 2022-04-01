package com.codinggame.chess.board.pieces;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Move;
import com.codinggame.chess.board.Square;

import java.util.List;

public class Bishop extends Piece {

    public Bishop(final Square square, Color color) {
        super(square, color);
        if (color.equals(Color.black)) {
            this.notation = "b";
        } else {
            this.notation = "B";
        }
    }

    @Override
    public Piece clonePiece() {
        return new Bishop(this.square, this.color);
    }

    @Override
    public List<Move> legalsMove(Board board) {
        return getMovesInDiagognale(board);


    }

    @Override
    public int getValue() {
        return 3;
    }
}
