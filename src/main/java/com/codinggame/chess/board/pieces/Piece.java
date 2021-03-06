package com.codinggame.chess.board.pieces;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Move;
import com.codinggame.chess.board.Square;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    public final Color color;
    public String notation;

    public Piece(Color color) {
        this.color = color;
    }

    public abstract List<Move> legalsMove(Board board, Square from);

    public abstract int getValue();

    public List<Move> getMovesInLine(final Board board, Square from) {
        List<Move> legalsMove = new ArrayList<>();

        List<Square> squaresInLine = getSquaresInLine(board, from);
        for (Square square : squaresInLine) {
            if (board.getPiece(square) == null) {
                legalsMove.add(new Move(this, from, square, false));
            } else if (board.getPiece(square).color != this.color) {
                legalsMove.add(new Move(true, this, from, square, board.getPiece(square), false));
            }
        }

        return legalsMove;
    }

    List<Square> getSquaresInLine(Board board, Square from) {
        List<Square> squares = new ArrayList<>();
        Color myColor = board.pieces[from.row][from.col].color;

        int currentX = from.row;
        int currentY = from.col;




        //sur la droite
        for (int i = currentY + 1; i < 8; i++) {
            Square right = Square.of(currentX, i);
            if (board.getPiece(right) == null) {
                squares.add(right);
            } else {
                squares.add(right);
                break;
            }
        }



        for (int i = currentY - 1; i >= 0; i--) {
            Square left = Square.of(currentX, i);
            if (board.getPiece(left) == null) {
                squares.add(left);
            } else {
                squares.add(left);

                break;
            }
        }

        //en haut
        for (int i = currentX - 1; i >= 0; i--) {
            Square up = Square.of(i, currentY);
            if (board.getPiece(up) == null) {
                squares.add(up);
            } else {
                squares.add(up);
                break;
            }
        }

        //en bas

        for (int i = currentX + 1; i < 8; i++) {
            Square bottom = Square.of(i, currentY);
            if (board.getPiece(bottom) == null) {
                squares.add(bottom);

            } else {
                squares.add(bottom);

                break;
            }
        }


        return squares;
    }

    List<Square> getSquareInDiagognale(final Board board, Square from) {
        List<Square> squares = new ArrayList<>();

        int currentX = from.row;
        int currentY = from.col;
        //en haut ?? gauche

        int x = currentX - 1;
        int y = currentY - 1;

        while (x >= 0 && y >= 0) {
            Square square = Square.of(x, y);
            if (board.getPiece(square) == null) {
                squares.add(square);
            } else {
                squares.add(square);
                break;
            }
            x--;
            y--;
        }
        //en haut ?? droite

        x = currentX - 1;
        y = currentY + 1;

        while (x >= 0 && y < 8) {
            Square square = Square.of(x, y);
            if (board.getPiece(square) == null) {
                squares.add(square);
            } else {
                squares.add(square);
                break;
            }
            x--;
            y++;
        }

        //en bas a gauche
        x = currentX + 1;
        y = currentY - 1;

        while (x < 8 && y >= 0) {
            Square square = Square.of(x, y);
            if (board.getPiece(square) == null) {
                squares.add(square);

            } else {
                squares.add(square);
                break;
            }
            x++;
            y--;
        }

        //en bas ?? droite
        x = currentX + 1;
        y = currentY + 1;

        while (x < 8 && y < 8) {
            Square square = Square.of(x, y);
            if (board.getPiece(square) == null) {
                squares.add(square);

            } else {
                squares.add(square);
                break;
            }
            x++;
            y++;
        }
        return squares;
    }

    public List<Move> getMovesInDiagognale(final Board board, Square from) {
        List<Move> legalMoves = new ArrayList<>();

        List<Square> squareInDiagognale = getSquareInDiagognale(board, from);
        for (Square square : squareInDiagognale) {
            if (board.getPiece(square) == null) {
                legalMoves.add(new Move(this, from, square, false));
            } else if (board.getPiece(square).color != this.color) {
                legalMoves.add(new Move(true, this, from, square, board.getPiece(square), false));
            }
        }
        return legalMoves;
    }

    public abstract List<Square> getControlledSquare(Board board, Square from);
}
