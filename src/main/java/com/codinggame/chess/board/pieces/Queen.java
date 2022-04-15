package com.codinggame.chess.board.pieces;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Move;
import com.codinggame.chess.board.Square;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
        if (color.equals(Color.black)) {
            this.notation = "q";
        } else {
            this.notation = "Q";
        }
    }

    @Override
    public List<Square> getControlledSquare(Board board, Square from) {
        List<Square> squares = new ArrayList<>();
        squares.addAll(this.getSquaresInLine(board,from));
        squares.addAll(this.getSquareInDiagognale(board,from));

        return squares;
    }

    @Override
    public List<Move> legalsMove(Board board,Square from) {
        List<Move> legalMoves1 = getMovesInDiagognale(board,from);
        List<Move> legalMoves2 = getMovesInLine(board,from);
        legalMoves1.addAll(legalMoves2);
        return legalMoves1;
    }

    @Override
    public int getValue() {
        return 9;
    }
}
