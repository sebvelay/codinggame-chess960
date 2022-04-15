package com.codinggame.chess.board;

import com.codinggame.chess.board.pieces.Piece;

import java.util.List;

public class Move {
    public final Square target;
    public final Square from;

    public final boolean isTakePiece;
    public final Piece piece;
    public final Piece takenPiece;
    public final boolean promote;
    public String move;
    public boolean isCheck = false;
    public Board boardAfterMove;

    public Move(final boolean isTakePiece, final Piece piece, final Square from, final Square target, final Piece takenPiece, final boolean promote) {
        this.isTakePiece = isTakePiece;
        this.target = target;
        this.piece = piece;
        this.takenPiece = takenPiece;
        this.move = from.translate + target.translate;
        if (promote) {
            move += "q";
        }
        this.promote = promote;
        this.from = from;
    }

    public Move(final Piece piece, final Square from, final Square target, final boolean promote) {
        this.isTakePiece = false;
        this.takenPiece = null;
        this.target = target;
        this.piece = piece;
        this.move = from.translate + target.translate;
        if (promote) {
            move += "q";
        }
        this.promote = promote;
        this.from = from;
    }

    public void setSimulateBoard(Board board) {
        this.boardAfterMove = board;
    }

    public boolean isSafeMove() {
        //for each piece on the board
        int controlledByOpponent = 0;
        int controlledByMyself = 0;
        for (Piece p : boardAfterMove.getPieces()) {
            List<Square> controlledSquare = p.getControlledSquare(boardAfterMove, this.from);
            if (controlledSquare.contains(target)) {
                if (p.color == piece.color) {
                    controlledByMyself++;
                } else {
                    controlledByOpponent++;
                }

            }

        }
        return controlledByMyself > controlledByOpponent;
    }
}
