package com.codinggame.chess.board.pieces;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Move;
import com.codinggame.chess.board.Square;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public King(Color color) {
        super(color);
        if (color.equals(Color.black)) {
            this.notation = "k";
        } else {
            this.notation = "K";
        }
    }


    @Override
    public List<Square> getControlledSquare(Board board, Square from) {
        List<Square> squares = new ArrayList<>();
        if (from.col - 1 >= 0) {
            Square targetLeft = Square.of(from.row, from.col - 1);
            squares.add(targetLeft);
        }
        if (from.col + 1 < 8) {
            Square targetRight = Square.of(from.row, from.col + 1);
            squares.add(targetRight);
        }
        if (from.row - 1 >= 0) {
            Square targetTop = Square.of(from.row - 1, from.col);
            squares.add(targetTop);
        }
        if (from.row + 1 < 8) {
            Square targetBottom = Square.of(from.row + 1, from.col);
            squares.add(targetBottom);
        }
        if (from.row - 1 >= 0 && from.col - 1 >= 0) {
            Square targetLeftTop = Square.of(from.row - 1, from.col - 1);
            squares.add(targetLeftTop);
        }
        if (from.row - 1 >= 0 && from.col + 1 < 8) {
            Square targetRightTop = Square.of(from.row - 1, from.col + 1);
            squares.add(targetRightTop);
        }
        if (from.row + 1 < 8 && from.col - 1 >= 0) {
            Square targetLeftBottom = Square.of(from.row + 1, from.col - 1);
            squares.add(targetLeftBottom);
        }
        if (from.row + 1 < 8 && from.col + 1 < 8) {
            Square targetRightBottom = Square.of(from.row + 1, from.col + 1);
            squares.add(targetRightBottom);
        }

        return squares;
    }

    @Override
    public List<Move> legalsMove(Board board, Square from) {
        List<Move> legalMoves = new ArrayList<>();

        Color opponent = this.color.equals(Color.white) ? Color.black : Color.white;
        //List<Piece> pieces = board.getPieces(opponent);
        List<Square> illegalSquare = new ArrayList<>();
        /*for (Piece p : pieces) {
            //get the current square of p


            illegalSquare.addAll(p.getControlledSquare(board, p.s));
        }*/
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                Piece piece = board.getPiece(Square.of(i, j));
                if(piece !=null && piece.color==opponent){
                    illegalSquare.addAll(piece.getControlledSquare(board, Square.of(i, j)));
                }
            }
        }


        for (Square s : getControlledSquare(board, from)) {
            if (!illegalSquare.contains(s)) {
                if (s.row >= 0 && s.row <= 7 && s.col >= 0 && s.col <= 7) {
                    if (board.getPiece(s) == null) {
                        legalMoves.add(new Move(this, from, s, false));
                    } else if (board.getPiece(s).color != this.color) {
                        legalMoves.add(new Move(true, this, from, s, board.getPiece(s), false));
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
