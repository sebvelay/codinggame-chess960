package com.codinggame.chess.board.pieces;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Move;
import com.codinggame.chess.board.Square;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public King(final Square square, Color color) {
        super(square, color);
        if (color.equals(Color.black)) {
            this.notation = "k";
        } else {
            this.notation = "K";
        }
    }

    @Override
    public Piece clonePiece() {
        return new King(this.square, this.color);
    }

    @Override
    public List<Square> getControlledSquare(Board board) {
        return new ArrayList<>();
    }

    @Override
    public List<Move> legalsMove(Board board) {
        List<Move> legalMoves = new ArrayList<>();

        Color opponent = this.color.equals(Color.white) ? Color.black : Color.white;
        List<Piece> pieces = board.getPieces(opponent);
        List<Square> illegalSquare = new ArrayList<>();
        for (Piece p : pieces) {
            illegalSquare.addAll(p.getControlledSquare(board));
        }

        List<Square> squares = new ArrayList<>();
        if (this.square.col - 1 >= 0) {
            Square targetLeft = Square.of(this.square.row, this.square.col - 1);
            squares.add(targetLeft);
        }
        if (this.square.col + 1 < 8) {
            Square targetRight = Square.of(this.square.row, this.square.col + 1);
            squares.add(targetRight);
        }
        if (this.square.row - 1 >= 0) {
            Square targetTop = Square.of(this.square.row - 1, this.square.col);
            squares.add(targetTop);
        }
        if (this.square.row + 1 < 8) {
            Square targetBottom = Square.of(this.square.row + 1, this.square.col);
            squares.add(targetBottom);
        }
        if (this.square.row - 1 >= 0 && this.square.col - 1 >= 0) {
            Square targetLeftTop = Square.of(this.square.row - 1, this.square.col - 1);
            squares.add(targetLeftTop);
        }
        if (this.square.row - 1 >= 0 && this.square.col + 1 < 8) {
            Square targetRightTop = Square.of(this.square.row - 1, this.square.col + 1);
            squares.add(targetRightTop);
        }
        if (this.square.row + 1 < 8 && this.square.col - 1 >= 0) {
            Square targetLeftBottom = Square.of(this.square.row + 1, this.square.col - 1);
            squares.add(targetLeftBottom);
        }
        if (this.square.row + 1 < 8 && this.square.col + 1 < 8) {
            Square targetRightBottom = Square.of(this.square.row + 1, this.square.col + 1);
            squares.add(targetRightBottom);
        }


        for (Square s : squares) {
            if (!illegalSquare.contains(s)) {
                if (s.row >= 0 && s.row <= 7 && s.col >= 0 && s.col <= 7) {
                    if (board.getPiece(s) == null) {
                        legalMoves.add(new Move(this, s, false));
                    } else if (board.getPiece(s).color != this.color) {
                        legalMoves.add(new Move(true, this, s, board.getPiece(s), false));
                    }
                }
            }

        }

        return legalMoves;
    }

    @Override
    public int getValue() {
        return 1000;
    }
}
