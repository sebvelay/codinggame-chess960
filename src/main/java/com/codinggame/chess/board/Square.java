package com.codinggame.chess.board;

import com.codinggame.chess.Cache;

import java.util.HashMap;
import java.util.Map;

public class Square {
    public static Map<String, Square> squaresPos = new HashMap<>(64);
    public static Map<String, Square> squaresRowCols = new HashMap<>(64);
    public final int row;
    public final int col;
    public final String translate;



    public Square(final int row, final int col) {
        this.row = row;
        this.col = col;
        this.translate = translate();
    }

    public static Square of(String pos) {
        return squaresPos.get(pos);
    }

    public static Square of(int row, int cols) {
        return squaresRowCols.get(row + "" + cols);
    }

    static {
        for (int i = 0; i < Cache.ROWS; i++) {
            for (int j = 0; j < Cache.COLS; j++) {
                Square square = new Square(i, j);
                squaresPos.put(square.translate, square);
                squaresRowCols.put(square.row + "" + square.col, square);
            }
        }
    }

    private String translate() {
        String pos = "";
        switch (this.col) {
            case 0:
                pos = "a";
                break;
            case 1:
                pos = "b";
                break;
            case 2:
                pos = "c";
                break;
            case 3:
                pos = "d";
                break;
            case 4:
                pos = "e";
                break;
            case 5:
                pos = "f";
                break;
            case 6:
                pos = "g";
                break;
            case 7:
                pos = "h";
                break;
        }


        return pos + (8 - row);
    }
}
