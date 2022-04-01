package com.codinggame.chess.board;

import com.codinggame.chess.board.Square;
import com.codinggame.chess.board.pieces.Piece;

public class Move {
    public final boolean isTakePiece;
    public final Piece piece;
    public final Square target;
    public final Piece takenPiece;
    public String move;
    public final boolean promote;
    public boolean isCheck = false;

    public Move(final boolean isTakePiece, final Piece piece, final Square target, final Piece takenPiece, final boolean promote) {
        this.isTakePiece = isTakePiece;
        this.target = target;
        this.piece = piece;
        this.takenPiece = takenPiece;
        this.move = piece.square.translate + target.translate;
        if (promote) {
            move += "q";
        }
        this.promote = promote;
    }

    public Move(final Piece piece, final Square target, final boolean promote) {
        this.isTakePiece = false;
        this.takenPiece = null;
        this.target = target;
        this.piece = piece;
        this.move = piece.square.translate + target.translate;
        if (promote) {
            move += "q";
        }
        this.promote = promote;
    }

}
