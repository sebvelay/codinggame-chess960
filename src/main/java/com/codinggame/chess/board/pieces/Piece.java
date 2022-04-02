package com.codinggame.chess.board.pieces;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Move;
import com.codinggame.chess.board.Square;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Piece {
    public final Color color;
    public Square square;
    public Map<Board, List<Move>> cachedMoves = new HashMap<>();
    public String notation;

    public Piece(Square square, Color color) {
        this.square = square;
        this.color = color;
    }

    public abstract List<Move> legalsMove(Board board);

    public abstract int getValue();

    public abstract Piece clonePiece();

    public List<Move> getMovesInLine(final Board board) {
        List<Move> legalsMove = new ArrayList<>();

        int currentX = this.square.row;
        int currentY = this.square.col;

        //sur la droite
        for (int i = currentY + 1; i < 8; i++) {
            Square right = Square.of(currentX, i);
            if (board.getPiece(right) == null) {
                legalsMove.add(new Move(this, right, false));
            } else if (board.getPiece(right).color != this.color) {
                legalsMove.add(new Move(true, this, right, board.getPiece(right), false));
                break;
            } else {
                break;
            }
        }

        //sur la gauche

        for (int i = currentY - 1; i >= 0; i--) {
            Square left = Square.of(currentX, i);
            if (board.getPiece(left) == null) {
                legalsMove.add(new Move(this, left, false));
            } else if (board.getPiece(left).color != this.color) {
                legalsMove.add(new Move(true, this, left, board.getPiece(left), false));
                break;
            } else {
                break;
            }
        }

        //en haut
        for (int i = currentX - 1; i >= 0; i--) {
            Square up = Square.of(i, currentY);
            if (board.getPiece(up) == null) {
                legalsMove.add(new Move(this, up, false));
            } else if (board.getPiece(up).color != this.color) {
                legalsMove.add(new Move(true, this, up, board.getPiece(up), false));
                break;
            } else {
                break;
            }
        }

        //en bas

        for (int i = currentX + 1; i < 8; i++) {
            Square up = Square.of(i, currentY);
            if (board.getPiece(up) == null) {
                legalsMove.add(new Move(this, up, false));
            } else if (board.getPiece(up).color != this.color) {
                legalsMove.add(new Move(true, this, up, board.getPiece(up), false));
                break;
            } else {
                break;
            }
        }

        return legalsMove;
    }

    public List<Move> getMovesInDiagognale(final Board board) {
        List<Move> legalMoves = new ArrayList<>();

        int currentX = this.square.row;
        int currentY = this.square.col;
        //en haut à gauche

        int x = currentX - 1;
        int y = currentY - 1;

        while (x >= 0 && y >= 0) {
            Square square = Square.of(x, y);
            if (board.getPiece(square) == null) {
                legalMoves.add(new Move(this, square, false));
            } else if (board.getPiece(square).color != this.color) {
                legalMoves.add(new Move(true, this, square, board.getPiece(square), false));
                break;
            } else {
                break;
            }
            x--;
            y--;
        }
        //en haut à droite

        x = currentX - 1;
        y = currentY + 1;

        while (x >= 0 && y < 8) {
            Square square = Square.of(x, y);
            if (board.getPiece(square) == null) {
                legalMoves.add(new Move(this, square, false));
            } else if (board.getPiece(square).color != this.color) {
                legalMoves.add(new Move(true, this, square, board.getPiece(square), false));
                break;
            } else {
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
                legalMoves.add(new Move(this, square, false));
            } else if (board.getPiece(square).color != this.color) {
                legalMoves.add(new Move(true, this, square, board.getPiece(square), false));
                break;
            } else {
                break;
            }
            x++;
            y--;
        }

        //en bas à droite
        x = currentX + 1;
        y = currentY + 1;

        while (x < 8 && y < 8) {
            Square square = Square.of(x, y);
            if (board.getPiece(square) == null) {
                legalMoves.add(new Move(this, square, false));
            } else if (board.getPiece(square).color != this.color) {
                legalMoves.add(new Move(true, this, square, board.getPiece(square), false));
                break;
            } else {
                break;
            }
            x++;
            y++;
        }

        return legalMoves;
    }

}
