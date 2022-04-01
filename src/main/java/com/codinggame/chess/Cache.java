package com.codinggame.chess;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Square;

import java.util.HashMap;
import java.util.Map;

public class Cache {

    public static Map<String, Board> cachedBoard = new HashMap<>();

    public static Square[][] squares = initBoard();


    public static final int ROWS = 8;
    public static final int COLS = 8;

    public static Square[][] initBoard() {
        Square[][] initSquares = new Square[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                initSquares[i][j] = new Square(i, j);
            }
        }
        return initSquares;
    }

}
