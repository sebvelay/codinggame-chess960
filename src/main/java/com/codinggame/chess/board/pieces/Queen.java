package com.codinggame.chess.board.pieces;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Move;
import com.codinggame.chess.board.Square;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    public Queen(final Square square, Color color) {
        super(square, color);
        if (color.equals(Color.black)) {
            this.notation = "q";
        } else {
            this.notation = "Q";
        }
    }

    @Override
    public Piece clonePiece() {
        return new Queen(this.square, this.color);
    }

    @Override
    public List<Square> getControlledSquare(Board board) {
        List<Square> squares = new ArrayList<>();
        squares.addAll(this.getSquaresInLine(board));
        squares.addAll(this.getSquareInDiagognale(board));

        return squares;
    }

    @Override
    public List<Move> legalsMove(Board board) {
        List<Move> legalMoves1 = getMovesInDiagognale(board);
        List<Move> legalMoves2 = getMovesInLine(board);
        legalMoves1.addAll(legalMoves2);
        return legalMoves1;
    }

    @Override
    public int getValue() {
        return 9;
    }
}
