package com.codinggame.chess;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.pieces.Color;
import com.codinggame.chess.evaluation.Evaluation;
import com.codinggame.chess.evaluation.Node;

import java.util.HashMap;
import java.util.List;


public class Game {

    public static int turn = 0;
    public static Color currentPlayer=null;

    public void play(String fen, String color, List<String> legalMovesFromGame) {
        turn++;
        Color myColor = color.equals("w") ? Color.white : Color.black;
        Game.currentPlayer = myColor;
        Board board = new Board(fen, legalMovesFromGame, myColor);
        Cache.cachedBoard = new HashMap<>();
        Cache.cachedBoard.put(fen, board);

        System.err.println("start evaluation " + Chrono.elapsedTime());

        Evaluation evaluation = new Evaluation(board, myColor, myColor, Constant.DEEPER);
        System.err.println("evaluate done");
        Node best = evaluation.getBest();
        if (best != null) {
            System.err.println("take a best " + best.move.move);
            if (legalMovesFromGame.contains(best.move.move)) {
                System.out.println(best.move.move + " " + Chrono.elapsedTime());
            } else {
                System.out.println("random");
            }
        } else {
            System.err.println("no best");
            System.out.println("random");

        }
    }

}


