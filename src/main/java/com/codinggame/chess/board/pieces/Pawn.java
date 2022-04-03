package com.codinggame.chess.board.pieces;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Move;
import com.codinggame.chess.board.Square;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    @Override
    public Piece clonePiece() {
        return new Pawn(this.square, this.color);
    }

    @Override
    public List<Square> getControlledSquare(Board board) {
        return new ArrayList<>();
    }

    public Pawn(final Square square, Color color) {
        super(square, color);
        if (color.equals(Color.black)) {
            this.notation = "p";
        } else {
            this.notation = "P";
        }
    }

    @Override
    public List<Move> legalsMove(Board board) {
        List<Move> legalsMove = new ArrayList<>();
        boolean promote = false;

        if (this.color.equals(Color.white)) {
            if (this.square.row - 1 >= 0) {
                Square target1 = Square.of(this.square.row - 1, this.square.col);
                if (this.square.row > 0 && board.getPiece(target1) == null) {
                    if (target1.row == 0) {
                        promote = true;
                    }
                    legalsMove.add(new Move(this, target1, promote));

                    //si je peux avancer de deux
                    if (this.square.row - 2 >= 0) {
                        Square target2 = Square.of(this.square.row - 2, this.square.col);
                        if (this.square.row == 6 && board.getPiece(target2) == null) {
                            legalsMove.add(new Move(this, target2, promote));
                        }
                    }
                }

                //si je peux prendre a droite
                if (this.square.row - 1 >= 0 && this.square.col + 1 < 8) {
                    Square targetTakeRight = Square.of(this.square.row - 1, this.square.col + 1);
                    if (board.getPiece(targetTakeRight) != null
                            && board.getPiece(targetTakeRight).color.equals(Color.black)) {
                        if (target1.row == 0) {
                            promote = true;
                        }
                        Piece takedPiece = board.getPiece(targetTakeRight);
                        Move move = new Move(true, this, targetTakeRight, takedPiece, promote);
                        legalsMove.add(move);
                    }
                }
                //si je peux prendre a gauche
                if (this.square.row - 1 >= 0 && this.square.col - 1 >= 0) {
                    Square targetTakeLeft = Square.of(this.square.row - 1, this.square.col - 1);
                    if (board.getPiece(targetTakeLeft) != null
                            && board.getPiece(targetTakeLeft).color.equals(Color.black)) {
                        if (target1.row == 0) {
                            promote = true;
                        }
                        Move move = new Move(true, this, targetTakeLeft, board.getPiece(targetTakeLeft), promote);
                        legalsMove.add(move);
                    }
                }

            }
        }

        if (this.color.equals(Color.black)) {
            if (this.square.row + 1 < 8) {
                Square target1 = Square.of(this.square.row + 1, this.square.col);
                if (this.square.row > 0 && board.getPiece(target1) == null) {
                    if (target1.row == 7) {
                        promote = true;
                    }
                    legalsMove.add(new Move(this, target1, promote));

                    //si je peux avancer de deux
                    if (this.square.row + 2 < 8) {
                        Square target2 = Square.of(this.square.row + 2, this.square.col);
                        if (this.square.row == 1 && board.getPiece(target2) == null) {
                            legalsMove.add(new Move(this, target2, promote));
                        }
                    }
                }
                //si je peux prendre a droite
                if (this.square.row + 1 < 8 && this.square.col + 1 < 8) {
                    Square targetTakeRight = Square.of(this.square.row + 1, this.square.col + 1);
                    if (board.getPiece(targetTakeRight) != null
                            && board.getPiece(targetTakeRight).color.equals(Color.white)) {
                        if (target1.row == 7) {
                            promote = true;
                        }
                        Move move = new Move(true, this, targetTakeRight, board.getPiece(targetTakeRight), promote);
                        legalsMove.add(move);
                    }
                }
                //si je peux prendre a gauche
                if (this.square.row + 1 < 8 && this.square.col - 1 >= 0) {
                    Square targetTakeLeft = Square.of(this.square.row + 1, this.square.col - 1);
                    if (board.getPiece(targetTakeLeft) != null
                            && board.getPiece(targetTakeLeft).color.equals(Color.white)) {
                        if (target1.row == 7) {
                            promote = true;
                        }
                        Move move = new Move(true, this, targetTakeLeft, board.getPiece(targetTakeLeft), promote);
                        legalsMove.add(move);
                    }
                }
            }

        }
        return legalsMove;
    }

    @Override
    public int getValue() {
        return 1;
    }
}
