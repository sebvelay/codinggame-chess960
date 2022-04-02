package com.codinggame.chess.board;

import com.codinggame.chess.Cache;
import com.codinggame.chess.board.pieces.*;
import com.codinggame.chess.board.score.MaterialScore;
import com.codinggame.chess.board.score.PositionScore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {


    public List<Piece> pieces = new ArrayList<>();

    public Map<Color, List<Move>> cachedMoves = new HashMap<>();
    public Map<Color, List<Piece>> cachedPieces = new HashMap<>();

    public String fen;

    public Integer score = null;

    private Board() {

    }

    /**
     * clone but don't put in cache
     *
     * @param board
     */
    public Board(Board board) {
        this.applyFen(board.fen);
    }

    public Board(final String fen) {
        this.applyFen(fen);
        Cache.cachedBoard.put(fen, this);
    }

    /*public Square getSquare(int row, int col) {
        return Cache.squares[row][col];
    }*/

    /**
     * Generate a new board and put on cache after move
     */
    public Board takePiece(Move m) {
        Board newBoard = new Board(this);
        Piece promoted = null;
        Piece toRemove = null;

        newBoard.pieces.removeIf(p -> m.target.equals(p.square));
        for (Piece p : newBoard.pieces) {
            if (p.square.equals(m.piece.square)) {
                p.square = m.target;
                if (m.move.endsWith("q")) {
                    promoted = new Queen(p.square, p.color);
                    toRemove = p;
                }
                break;
            }
        }

        if (toRemove != null && promoted != null) {
            newBoard.pieces.remove(toRemove);
            newBoard.pieces.add(promoted);
        }

        newBoard.recalculateFenAndPutOnCache();
        return newBoard;
    }

    /**
     * Generate a new board and put on cache after move
     */
    public Board move(final Move m) {
        Board newBoard = new Board(this);
        Piece promoted = null;
        Piece toRemove = null;
        for (Piece p : newBoard.pieces) {
            if (p.square.equals(m.piece.square)) {
                p.square = m.target;
                if (m.move.endsWith("q")) {
                    promoted = new Queen(p.square, p.color);
                    toRemove = p;
                }
            }
        }
        if (toRemove != null && promoted != null) {
            newBoard.pieces.remove(toRemove);
            newBoard.pieces.add(promoted);
        }

        newBoard.recalculateFenAndPutOnCache();
        return newBoard;

    }

    private String recalculateFen() {
        //on recalcule le fen
        String newFen = "";
        for (int i = 0; i < Cache.ROWS; i++) {
            int empty = 0;
            if (i > 0) {
                newFen += "/";
            }
            for (int j = 0; j < Cache.COLS; j++) {
                Square square = Square.of(i, j);
                Piece piece = this.getPiece(square);
                if (piece == null) {
                    empty++;
                } else {
                    if (empty > 0) {
                        newFen += empty;
                    }
                    empty = 0;
                    newFen += piece.notation;
                }

            }
            if (empty > 0) {
                newFen += empty;
            }
        }
        return newFen;
    }

    private void recalculateFenAndPutOnCache() {
        this.cachedMoves = new HashMap<>();
        this.cachedPieces = new HashMap<>();
        this.fen = recalculateFen();
        Cache.cachedBoard.put(this.fen, this);
    }


    public List<Piece> getPieces(Color color) {
        if (cachedPieces.containsKey(color)) {
            //System.err.println("used cached pieces");
            return cachedPieces.get(color);
        }
        List<Piece> colorPiece = new ArrayList<>();
        for (Piece p : this.pieces) {
            if (p.color.equals(color)) {
                colorPiece.add(p);
            }
        }
        cachedPieces.put(color, colorPiece);
        return colorPiece;
    }

    public Piece getPiece(Square square) {
        for (Piece p : pieces) {
            if (p.square.equals(square)) {
                return p;
            }
        }
        return null;
    }


    public List<Move> getMoves(Color color) {

        if (cachedMoves.containsKey(color)) {
            //System.err.println("used cached moves");
            return cachedMoves.get(color);
        }
        List<Move> moves = this.getPieces(color).parallelStream()
                .map(p -> p.legalsMove(this))
                .flatMap(n -> n.parallelStream())
                .collect(Collectors.toList());
        cachedMoves.put(color, moves);
        return moves;
    }

    public void applyFen(String fen) {
        this.fen = fen;
        int col = 0; //x
        int row = 0; //y
        char[] chars = fen.toCharArray();
        for (char b : chars) {
            if (b == '/') {
                col = 0;
                row++;
            } else if (b > '0' && b <= '8') {
                int j = Integer.parseInt(String.valueOf(b));
                col = col + j;
            } else {
                Piece piece = getPieceFromFenSymbol(b, Square.of(row, col));
                pieces.add(piece);
                col++;
            }
        }
    }

    Piece getPieceFromFenSymbol(char f, Square currentSquare) {
        Piece piece = null;
        if (f == 'r') {
            piece = new Rook(currentSquare, Color.black);
        } else if (f == 'R') {
            piece = new Rook(currentSquare, Color.white);
        } else if (f == 'k') {
            piece = new King(currentSquare, Color.black);
        } else if (f == 'K') {
            piece = new King(currentSquare, Color.white);
        } else if (f == 'B') {
            piece = new Bishop(currentSquare, Color.white);
        } else if (f == 'b') {
            piece = new Bishop(currentSquare, Color.black);
        } else if (f == 'Q') {
            piece = new Queen(currentSquare, Color.white);
        } else if (f == 'q') {
            piece = new Queen(currentSquare, Color.black);
        } else if (f == 'n') {
            piece = new Knight(currentSquare, Color.black);
        } else if (f == 'N') {
            piece = new Knight(currentSquare, Color.white);
        } else if (f == 'P') {
            piece = new Pawn(currentSquare, Color.white);
        } else if (f == 'p') {
            piece = new Pawn(currentSquare, Color.black);
        }
        return piece;
    }

    public boolean isCheck(Color color) {
        Color opponent = Color.black;
        if (color.equals(Color.black)) {
            opponent = Color.white;
        }
        List<Piece> pieces = this.getPieces(opponent);
        for (Piece p : pieces) {
            for (Move m : p.legalsMove(this)) {
                if (m.isTakePiece && m.takenPiece instanceof King) {
                    return true;
                }
            }
        }
        return false;
    }


    public String getFen() {
        return this.fen;
    }

    public int getScore() {
        if (score != null) {
            return score;
        }

        int scoreWhite = MaterialScore.evaluate(this, Color.white);
        int scoreBlack = MaterialScore.evaluate(this, Color.black);
        scoreWhite += PositionScore.evaluate(this, Color.white);
        scoreBlack += PositionScore.evaluate(this, Color.black);
        int score = 0;


        score = scoreWhite - scoreBlack;
        this.score = score;
        return score;
    }
}
