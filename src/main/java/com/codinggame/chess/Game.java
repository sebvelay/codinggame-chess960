package com.codinggame.chess;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.pieces.Color;
import com.codinggame.chess.board.pieces.Piece;
import com.codinggame.chess.evaluation.Evaluation;
import com.codinggame.chess.evaluation.Node;

import java.util.HashMap;
import java.util.List;


public class Game extends Chrono {

    public static int turn=0;



    HashMap<Piece, String> pieces = new HashMap<>(16);

    public Game(long start) {
        Chrono.start = start;
    }

    public Game() {
        Chrono.start = System.currentTimeMillis();
    }


    public void play(String fen, String color, List<String> legalMovesFromGame) {
        turn++;
        Board board = null;
        if (Cache.cachedBoard.containsKey(fen)) {
            board = Cache.cachedBoard.get(fen);
        } else {
            board = new Board(fen);
        }

        Color myColor = color.equals("w") ? Color.white : Color.black;

        System.err.println("elpasedTime : " + elapsedTime());


        System.err.println("start evaluation " + elapsedTime());

        Evaluation evaluation = new Evaluation(board, myColor, myColor,Constant.DEEPER);
        System.err.println("evaluate done");
        Node best = evaluation.getBest();
        if (best != null) {
            System.err.println("take a best " + best.move.move);
            if (legalMovesFromGame.contains(best.move.move)) {
                System.out.println(best.move.move);
            } else {
                System.out.println("random");
            }
        } else {
            System.err.println("no best");
            System.out.println("random");

        }
    }

}


