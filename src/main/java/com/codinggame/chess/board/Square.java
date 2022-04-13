package com.codinggame.chess.board;

public class Square {
    static Square a8 = new Square(0, 0);
    static Square e4 = new Square(4, 4);
    static Square b8 = new Square(0, 1);
    static Square f4 = new Square(4, 5);
    static Square c8 = new Square(0, 2);
    static Square g4 = new Square(4, 6);
    static Square d8 = new Square(0, 3);
    static Square h4 = new Square(4, 7);
    static Square e8 = new Square(0, 4);
    static Square f8 = new Square(0, 5);
    static Square g8 = new Square(0, 6);
    static Square h8 = new Square(0, 7);
    static Square a3 = new Square(5, 0);
    static Square b3 = new Square(5, 1);
    static Square c3 = new Square(5, 2);
    static Square d3 = new Square(5, 3);
    static Square a7 = new Square(1, 0);
    static Square e3 = new Square(5, 4);
    static Square b7 = new Square(1, 1);
    static Square f3 = new Square(5, 5);
    static Square c7 = new Square(1, 2);
    static Square g3 = new Square(5, 6);
    static Square d7 = new Square(1, 3);
    static Square h3 = new Square(5, 7);
    static Square e7 = new Square(1, 4);
    static Square f7 = new Square(1, 5);
    static Square g7 = new Square(1, 6);
    static Square h7 = new Square(1, 7);
    static Square a2 = new Square(6, 0);
    static Square b2 = new Square(6, 1);
    static Square c2 = new Square(6, 2);
    static Square d2 = new Square(6, 3);
    static Square a6 = new Square(2, 0);
    static Square e2 = new Square(6, 4);
    static Square b6 = new Square(2, 1);
    static Square f2 = new Square(6, 5);
    static Square c6 = new Square(2, 2);
    static Square g2 = new Square(6, 6);
    static Square d6 = new Square(2, 3);
    static Square h2 = new Square(6, 7);
    static Square e6 = new Square(2, 4);
    static Square f6 = new Square(2, 5);
    static Square g6 = new Square(2, 6);
    static Square h6 = new Square(2, 7);
    static Square a1 = new Square(7, 0);
    static Square b1 = new Square(7, 1);
    static Square c1 = new Square(7, 2);
    static Square d1 = new Square(7, 3);
    static Square a5 = new Square(3, 0);
    static Square e1 = new Square(7, 4);
    static Square b5 = new Square(3, 1);
    static Square f1 = new Square(7, 5);
    static Square c5 = new Square(3, 2);
    static Square g1 = new Square(7, 6);
    static Square d5 = new Square(3, 3);
    static Square h1 = new Square(7, 7);
    static Square e5 = new Square(3, 4);
    static Square f5 = new Square(3, 5);
    static Square g5 = new Square(3, 6);
    static Square h5 = new Square(3, 7);
    static Square a4 = new Square(4, 0);
    static Square b4 = new Square(4, 1);
    static Square c4 = new Square(4, 2);
    static Square d4 = new Square(4, 3);
    public final int row;
    public final int col;
    public final String translate;


    public Square(final int row, final int col) {
        this.row = row;
        this.col = col;
        this.translate = translate();
    }

    public static Square of(String pos) {

        if (pos.equals("a8")) return a8;
        if (pos.equals("e4")) return e4;
        if (pos.equals("b8")) return b8;
        if (pos.equals("f4")) return f4;
        if (pos.equals("c8")) return c8;
        if (pos.equals("g4")) return g4;
        if (pos.equals("d8")) return d8;
        if (pos.equals("h4")) return h4;
        if (pos.equals("e8")) return e8;
        if (pos.equals("f8")) return f8;
        if (pos.equals("g8")) return g8;
        if (pos.equals("h8")) return h8;
        if (pos.equals("a3")) return a3;
        if (pos.equals("b3")) return b3;
        if (pos.equals("c3")) return c3;
        if (pos.equals("d3")) return d3;
        if (pos.equals("a7")) return a7;
        if (pos.equals("e3")) return e3;
        if (pos.equals("b7")) return b7;
        if (pos.equals("f3")) return f3;
        if (pos.equals("c7")) return c7;
        if (pos.equals("g3")) return g3;
        if (pos.equals("d7")) return d7;
        if (pos.equals("h3")) return h3;
        if (pos.equals("e7")) return e7;
        if (pos.equals("f7")) return f7;
        if (pos.equals("g7")) return g7;
        if (pos.equals("h7")) return h7;
        if (pos.equals("a2")) return a2;
        if (pos.equals("b2")) return b2;
        if (pos.equals("c2")) return c2;
        if (pos.equals("d2")) return d2;
        if (pos.equals("a6")) return a6;
        if (pos.equals("e2")) return e2;
        if (pos.equals("b6")) return b6;
        if (pos.equals("f2")) return f2;
        if (pos.equals("c6")) return c6;
        if (pos.equals("g2")) return g2;
        if (pos.equals("d6")) return d6;
        if (pos.equals("h2")) return h2;
        if (pos.equals("e6")) return e6;
        if (pos.equals("f6")) return f6;
        if (pos.equals("g6")) return g6;
        if (pos.equals("h6")) return h6;
        if (pos.equals("a1")) return a1;
        if (pos.equals("b1")) return b1;
        if (pos.equals("c1")) return c1;
        if (pos.equals("d1")) return d1;
        if (pos.equals("a5")) return a5;
        if (pos.equals("e1")) return e1;
        if (pos.equals("b5")) return b5;
        if (pos.equals("f1")) return f1;
        if (pos.equals("c5")) return c5;
        if (pos.equals("g1")) return g1;
        if (pos.equals("d5")) return d5;
        if (pos.equals("h1")) return h1;
        if (pos.equals("e5")) return e5;
        if (pos.equals("f5")) return f5;
        if (pos.equals("g5")) return g5;
        if (pos.equals("h5")) return h5;
        if (pos.equals("a4")) return a4;
        if (pos.equals("b4")) return b4;
        if (pos.equals("c4")) return c4;
        if (pos.equals("d4")) return d4;
        return null;
    }

    public static Square of(int row, int col) {
        if(row == 0 && col == 0) return a8;
        if(row == 4 && col == 4) return e4;
        if(row == 0 && col == 1) return b8;
        if(row == 4 && col == 5) return f4;
        if(row == 0 && col == 2) return c8;
        if(row == 4 && col == 6) return g4;
        if(row == 0 && col == 3) return d8;
        if(row == 4 && col == 7) return h4;
        if(row == 0 && col == 4) return e8;
        if(row == 0 && col == 5) return f8;
        if(row == 0 && col == 6) return g8;
        if(row == 0 && col == 7) return h8;
        if(row == 5 && col == 0) return a3;
        if(row == 5 && col == 1) return b3;
        if(row == 5 && col == 2) return c3;
        if(row == 5 && col == 3) return d3;
        if(row == 1 && col == 0) return a7;
        if(row == 5 && col == 4) return e3;
        if(row == 1 && col == 1) return b7;
        if(row == 5 && col == 5) return f3;
        if(row == 1 && col == 2) return c7;
        if(row == 5 && col == 6) return g3;
        if(row == 1 && col == 3) return d7;
        if(row == 5 && col == 7) return h3;
        if(row == 1 && col == 4) return e7;
        if(row == 1 && col == 5) return f7;
        if(row == 1 && col == 6) return g7;
        if(row == 1 && col == 7) return h7;
        if(row == 6 && col == 0) return a2;
        if(row == 6 && col == 1) return b2;
        if(row == 6 && col == 2) return c2;
        if(row == 6 && col == 3) return d2;
        if(row == 2 && col == 0) return a6;
        if(row == 6 && col == 4) return e2;
        if(row == 2 && col == 1) return b6;
        if(row == 6 && col == 5) return f2;
        if(row == 2 && col == 2) return c6;
        if(row == 6 && col == 6) return g2;
        if(row == 2 && col == 3) return d6;
        if(row == 6 && col == 7) return h2;
        if(row == 2 && col == 4) return e6;
        if(row == 2 && col == 5) return f6;
        if(row == 2 && col == 6) return g6;
        if(row == 2 && col == 7) return h6;
        if(row == 7 && col == 0) return a1;
        if(row == 7 && col == 1) return b1;
        if(row == 7 && col == 2) return c1;
        if(row == 7 && col == 3) return d1;
        if(row == 3 && col == 0) return a5;
        if(row == 7 && col == 4) return e1;
        if(row == 3 && col == 1) return b5;
        if(row == 7 && col == 5) return f1;
        if(row == 3 && col == 2) return c5;
        if(row == 7 && col == 6) return g1;
        if(row == 3 && col == 3) return d5;
        if(row == 7 && col == 7) return h1;
        if(row == 3 && col == 4) return e5;
        if(row == 3 && col == 5) return f5;
        if(row == 3 && col == 6) return g5;
        if(row == 3 && col == 7) return h5;
        if(row == 4 && col == 0) return a4;
        if(row == 4 && col == 1) return b4;
        if(row == 4 && col == 2) return c4;
        if(row == 4 && col == 3) return d4;
        return null;
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

    /*public static void main(String[] args) {
        Map<String, Square> squaresRowCols = new HashMap<>(64);

        for (int i = 0; i < Cache.ROWS; i++) {
            for (int j = 0; j < Cache.COLS; j++) {
                Square square = new Square(i, j);
                squaresRowCols.put(square.row + "" + square.col, square);
            }
        }

        System.out.println("generate Squares");
        for (Square s : squaresRowCols.values()) {
            System.out.println("static Square " + s.translate + " = new Square(" + s.row + ", " + s.col + ");");
        }
        System.out.println("square by pos");

        for (Square s : squaresRowCols.values()) {
            System.out.println("if(pos.equals(\"" + s.translate + "\")) return " + s.translate + ";");
        }

        System.out.println("square by coord");
        for (Square s : squaresRowCols.values()) {
            System.out.println("if(row == " + s.row + " && col == " + s.col + ") return " + s.translate + ";");
        }
    }*/
}
