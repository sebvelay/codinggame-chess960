package com.codinggame.chess.board.pieces;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Move;
import com.codinggame.chess.board.Square;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
        if (color.equals(Color.black)) {
            this.notation = "n";
        } else {
            this.notation = "N";
        }
    }

    @Override
    public List<Move> legalsMove(Board board, Square from) {
        List<Move> legalMoves = new ArrayList<>();


        for (Square s : getControlledSquare(board, from)) {
            if (s.row >= 0 && s.row <= 7 && s.col >= 0 && s.col <= 7) {
                if (board.getPiece(s) == null) {
                    legalMoves.add(new Move(this, from, s, false));
                } else if (board.getPiece(s).color != this.color) {
                    legalMoves.add(new Move(true, this, from, s, board.getPiece(s), false));
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
    public List<Square> getControlledSquare(Board board, Square from) {
        List<Square> squareList = new ArrayList<>();

        if (from.row - 1 >= 0 && from.col - 2 >= 0) {
            Square target1 = Square.of(from.row - 1, from.col - 2);
            squareList.add(target1);
        }
        if (from.row - 2 >= 0 && from.col - 1 >= 0) {
            Square target2 = Square.of(from.row - 2, from.col - 1);
            squareList.add(target2);
        }
        if (from.row - 1 >= 0 && from.col + 2 < 8) {
            Square target3 = Square.of(from.row - 1, from.col + 2);
            squareList.add(target3);
        }
        if (from.row - 2 >= 0 && from.col + 1 < 8) {
            Square target4 = Square.of(from.row - 2, from.col + 1);
            squareList.add(target4);
        }
        if (from.row + 1 < 8 && from.col + 2 < 8) {
            Square target5 = Square.of(from.row + 1, from.col + 2);
            squareList.add(target5);
        }
        if (from.row + 2 < 8 && from.col + 1 < 8) {
            Square target6 = Square.of(from.row + 2, from.col + 1);
            squareList.add(target6);
        }
        if (from.row + 2 < 8 && from.col - 1 >= 0) {
            Square target7 = Square.of(from.row + 2, from.col - 1);
            squareList.add(target7);
        }
        if (from.row + 1 < 8 && from.col - 2 >= 0) {
            Square target8 = Square.of(from.row + 1, from.col - 2);
            squareList.add(target8);
        }
        return squareList;
    }
}
