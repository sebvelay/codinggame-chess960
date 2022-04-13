package com.codinggame.chess.board;

import com.codinggame.chess.Cache;
import com.codinggame.chess.board.pieces.*;
import com.codinggame.chess.board.score.CheckScore;
import com.codinggame.chess.board.score.MaterialScore;
import com.codinggame.chess.board.score.PositionScore;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public String fen;
    public Integer score = null;
    private Piece[][] pieces = new Piece[8][8];
    private List<Move> legalsMove = null;

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

    public Board(String fen, List<String> legalsMove, Color color) {
        this.applyFen(fen);
        List<Move> moves = getMoves(color);
        List<Move> legals = new ArrayList<>();
        for (Move move : moves) {
            if (legalsMove.contains(move.move)) {
                legals.add(move);
            }
        }
        //on met à jour le cache
        this.legalsMove = legals;
        Cache.cachedBoard.put(fen, this);

    }

    public Board move(final Move m) {
        Board newBoard;
        if (m.isTakePiece) {
            newBoard = this.takePiece(m);
        } else {
            newBoard = this.movePiece(m);
        }
        m.setSimulateBoard(newBoard);
        return newBoard;
    }

    /**
     * Generate a new board and put on cache after move
     */
    private Board takePiece(final Move m) {
        Board newBoard = new Board(this);
        Piece promoted = null;
        Piece toRemove = null;

        newBoard.pieces[m.from.row][m.from.col] = null;


        if (m.move.endsWith("q")) {
            promoted = new Queen(m.target, m.piece.color);
            newBoard.pieces[m.target.row][m.target.col] = promoted;
            newBoard.pieces[m.target.row][m.target.col].square = m.target;
        } else {
            newBoard.pieces[m.target.row][m.target.col] = m.piece;
            newBoard.pieces[m.target.row][m.target.col].square = m.target;
        }

        /*for (Piece p : newBoard.pieces) {
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
        }*/

        newBoard.recalculateFenAndPutOnCache();
        return newBoard;
    }

    /**
     * Generate a new board and put on cache after move
     */
    private Board movePiece(final Move m) {
        Board newBoard = new Board(this);
        Piece promoted = null;

        if (m.move.endsWith("q")) {
            promoted = new Queen(m.target, m.piece.color);
            newBoard.pieces[m.target.row][m.target.col] = promoted;
            newBoard.pieces[m.target.row][m.target.col].square = m.target;
        } else {
            newBoard.pieces[m.from.row][m.from.col] = null;
            newBoard.pieces[m.target.row][m.target.col] = m.piece;
            newBoard.pieces[m.target.row][m.target.col].square = m.target;
        }
        /*for (Piece p : newBoard.pieces) {
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
        }*/

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
                //Square square = Square.of(i, j);
                Piece piece = this.pieces[i][j];
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
        this.fen = recalculateFen();
        Cache.cachedBoard.put(this.fen, this);
    }


    public List<Piece> getPieces(Color color) {

        List<Piece> colorPiece = new ArrayList<>();
        for (Piece[] row : pieces) {
            for (Piece piece : row) {
                if (piece != null && piece.color == color) {
                    colorPiece.add(piece);
                }
            }
        }

        return colorPiece;
    }

    public Piece getPiece(Square square) {
        return pieces[square.row][square.col];
    }


    public List<Move> getMoves(Color color) {
        if (legalsMove != null) {
            return legalsMove;
        }


        List<Move> moves = new ArrayList<>();
        for (Piece p : this.getPieces(color)) {
            List<Move> legalsMove = p.legalsMove(this);
            moves.addAll(legalsMove);
        }
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
                pieces[row][col] = piece;
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

    public boolean isDoACheck(Color color) {
        List<Move> moves = getMoves(color);
        for (Move m : moves) {
            if (m.isTakePiece && m.takenPiece instanceof King) {
                return true;
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
        scoreWhite += CheckScore.evaluate(this, Color.white);
        scoreBlack += CheckScore.evaluate(this, Color.black);
        int score = 0;


        score = scoreWhite - scoreBlack;
        this.score = score;
        return score;
    }

    public List<Piece> getPieces() {
        List<Piece> allPieces = new ArrayList<>();
        for (Piece[] row : pieces) {
            for (Piece piece : row) {
                if (piece != null) {
                    allPieces.add(piece);
                }
            }
        }
        return allPieces;
    }
}
