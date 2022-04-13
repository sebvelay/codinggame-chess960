package com.codinggame.chess.board.pieces;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Move;
import com.codinggame.chess.board.Square;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(final Square square, Color color) {
        super(square, color);
        if (color.equals(Color.black)) {
            this.notation = "n";
        } else {
            this.notation = "N";
        }
    }

    @Override
    public List<Move> legalsMove(Board board) {
        List<Move> legalMoves = new ArrayList<>();


        for (Square s : getControlledSquare(board)) {
            if (s.row >= 0 && s.row <= 7 && s.col >= 0 && s.col <= 7) {
                if (board.getPiece(s) == null) {
                    legalMoves.add(new Move(this, s, false));
                } else if (board.getPiece(s).color != this.color) {
                    legalMoves.add(new Move(true, this, s, board.getPiece(s), false));
                }
            }
        }
        return legalMoves;
    }

    @Override
    public int getValue() {
        return 3;
    }

    @Override
    public Piece clonePiece() {
        return new Knight(this.square, this.color);
    }

    @Override
    public List<Square> getControlledSquare(Board board) {
        List<Square> squareList = new ArrayList<>();

        if (this.square.row - 1 >= 0 && this.square.col - 2 >= 0) {
            Square target1 = Square.of(this.square.row - 1, this.square.col - 2);
            squareList.add(target1);
        }
        if (this.square.row - 2 >= 0 && this.square.col - 1 >= 0) {
            Square target2 = Square.of(this.square.row - 2, this.square.col - 1);
            squareList.add(target2);
        }
        if (this.square.row - 1 >= 0 && this.square.col + 2 < 8) {
            Square target3 = Square.of(this.square.row - 1, this.square.col + 2);
            squareList.add(target3);
        }
        if (this.square.row - 2 >= 0 && this.square.col + 1 < 8) {
            Square target4 = Square.of(this.square.row - 2, this.square.col + 1);
            squareList.add(target4);
        }
        if (this.square.row + 1 < 8 && this.square.col + 2 < 8) {
            Square target5 = Square.of(this.square.row + 1, this.square.col + 2);
            squareList.add(target5);
        }
        if (this.square.row + 2 < 8 && this.square.col + 1 < 8) {
            Square target6 = Square.of(this.square.row + 2, this.square.col + 1);
            squareList.add(target6);
        }
        if (this.square.row + 2 < 8 && this.square.col - 1 >= 0) {
            Square target7 = Square.of(this.square.row + 2, this.square.col - 1);
            squareList.add(target7);
        }
        if (this.square.row + 1 < 8 && this.square.col - 2 >= 0) {
            Square target8 = Square.of(this.square.row + 1, this.square.col - 2);
            squareList.add(target8);
        }
        return squareList;
    }
}
