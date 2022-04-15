package com.codinggame.chess.board.pieces;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Move;
import com.codinggame.chess.board.Square;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {


    public Pawn(Color color) {
        super(color);
        if (color.equals(Color.black)) {
            this.notation = "p";
        } else {
            this.notation = "P";
        }
    }

    @Override
    public List<Square> getControlledSquare(Board board, Square from) {
        ArrayList<Square> squares = new ArrayList<>();
        if (this.color.equals(Color.white)) {
            if (from.row - 1 >= 0 && from.col + 1 < 8) {
                Square targetTakeRight = Square.of(from.row - 1, from.col + 1);
                squares.add(targetTakeRight);
            }
            //si je peux prendre a gauche
            if (from.row - 1 >= 0 && from.col - 1 >= 0) {
                Square targetTakeLeft = Square.of(from.row - 1, from.col - 1);
                squares.add(targetTakeLeft);

            }
        }
        if (this.color.equals(Color.black)) {
            if (from.row + 1 < 8 && from.col + 1 < 8) {
                Square targetTakeRight = Square.of(from.row + 1, from.col + 1);
                squares.add(targetTakeRight);

            }
            if (from.row + 1 < 8 && from.col - 1 >= 0) {
                Square targetTakeLeft = Square.of(from.row + 1, from.col - 1);
                squares.add(targetTakeLeft);

            }
        }
        return squares;
    }

    @Override
    public List<Move> legalsMove(Board board, Square from) {
        List<Move> legalsMove = new ArrayList<>();
        boolean promote = false;

        if (this.color.equals(Color.white)) {
            if (from.row - 1 >= 0) {
                Square target1 = Square.of(from.row - 1, from.col);
                if (from.row > 0 && board.getPiece(target1) == null) {
                    if (target1.row == 0) {
                        promote = true;
                    }
                    legalsMove.add(new Move(this, from, target1, promote));

                    //si je peux avancer de deux
                    if (from.row - 2 >= 0) {
                        Square target2 = Square.of(from.row - 2, from.col);
                        if (from.row == 6 && board.getPiece(target2) == null) {
                            legalsMove.add(new Move(this, from, target2, promote));
                        }
                    }
                }

                //si je peux prendre a droite
                if (from.row - 1 >= 0 && from.col + 1 < 8) {
                    Square targetTakeRight = Square.of(from.row - 1, from.col + 1);
                    if (board.getPiece(targetTakeRight) != null
                            && board.getPiece(targetTakeRight).color.equals(Color.black)) {
                        if (target1.row == 0) {
                            promote = true;
                        }
                        Piece takedPiece = board.getPiece(targetTakeRight);
                        Move move = new Move(true, this, from, targetTakeRight, takedPiece, promote);
                        legalsMove.add(move);
                    }
                }
                //si je peux prendre a gauche
                if (from.row - 1 >= 0 && from.col - 1 >= 0) {
                    Square targetTakeLeft = Square.of(from.row - 1, from.col - 1);
                    if (board.getPiece(targetTakeLeft) != null
                            && board.getPiece(targetTakeLeft).color.equals(Color.black)) {
                        if (target1.row == 0) {
                            promote = true;
                        }
                        Move move = new Move(true, this, from, targetTakeLeft, board.getPiece(targetTakeLeft), promote);
                        legalsMove.add(move);
                    }
                }

            }
        }

        if (this.color.equals(Color.black)) {
            if (from.row + 1 < 8) {
                Square target1 = Square.of(from.row + 1, from.col);
                if (from.row > 0 && board.getPiece(target1) == null) {
                    if (target1.row == 7) {
                        promote = true;
                    }
                    legalsMove.add(new Move(this, from, target1, promote));

                    //si je peux avancer de deux
                    if (from.row + 2 < 8) {
                        Square target2 = Square.of(from.row + 2, from.col);
                        if (from.row == 1 && board.getPiece(target2) == null) {
                            legalsMove.add(new Move(this, from, target2, promote));
                        }
                    }
                }
                //si je peux prendre a droite
                if (from.row + 1 < 8 && from.col + 1 < 8) {
                    Square targetTakeRight = Square.of(from.row + 1, from.col + 1);
                    if (board.getPiece(targetTakeRight) != null
                            && board.getPiece(targetTakeRight).color.equals(Color.white)) {
                        if (target1.row == 7) {
                            promote = true;
                        }
                        Move move = new Move(true, this, from, targetTakeRight, board.getPiece(targetTakeRight), promote);
                        legalsMove.add(move);
                    }
                }
                //si je peux prendre a gauche
                if (from.row + 1 < 8 && from.col - 1 >= 0) {
                    Square targetTakeLeft = Square.of(from.row + 1, from.col - 1);
                    if (board.getPiece(targetTakeLeft) != null
                            && board.getPiece(targetTakeLeft).color.equals(Color.white)) {
                        if (target1.row == 7) {
                            promote = true;
                        }
                        Move move = new Move(true, this, from, targetTakeLeft, board.getPiece(targetTakeLeft), promote);
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
