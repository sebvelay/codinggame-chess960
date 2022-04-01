package com.codinggame.chess.board;

import java.util.Objects;

public class Square {
    public final int row;
    public final int col;
    public final String translate;

    public Square(final int row, final int col) {
        this.row = row;
        this.col = col;
        this.translate = translate();
    }

    public Square(final String pos) {
        String part1 = pos.substring(0, 1);
        String part2 = pos.substring(1, 2);
        //a1 = 7,0
        //d6 = 2,3

        this.row = 8 - Integer.parseInt(part2);
        String letters = "abcdefgh";
        this.col = letters.indexOf(part1);
        this.translate = pos;
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

    @Override
    public boolean equals(final Object o) {
        final Square square = (Square) o;
        return row == square.row && col == square.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
