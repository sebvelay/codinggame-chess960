package com.codinggame.chess;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Move;
import com.codinggame.chess.board.pieces.Color;
import com.codinggame.chess.board.pieces.Piece;
import com.codinggame.chess.evaluation.Evaluation;
import com.codinggame.chess.evaluation.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Game extends Chrono {

    public static Piece LAST_PIECE = null;



    HashMap<Piece, String> pieces = new HashMap<>(16);

    public Game(long start) {
        Chrono.start = start;
    }

    public Game() {
        Chrono.start = System.currentTimeMillis();
    }


    public void play(String fen, String color, List<String> legalMovesFromGame) {
        Board board = null;
        if (Cache.cachedBoard.containsKey(fen)) {
            board = Cache.cachedBoard.get(fen);
        } else {
            board = new Board(fen);
        }

        Color myColor = color.equals("w") ? Color.white : Color.black;
        List<Piece> pieces = board.getPieces(myColor);
        if (fen.contains("/pppppppp/8/8/8/8/PPPPPPPP/")) {
            pieces.removeIf(p -> p.getValue() > 1);
        }

        List<Move> moveList = board.getMoves(myColor);
        /**
         * hack temporaire pour vérifier qu'on a pas caculé de mauvais move
         */
        if (legalMovesFromGame != null) {
            List<Move> moveListValide = new ArrayList<>();
            for (Move m : moveList) {
                if (legalMovesFromGame.contains(m.move)) {
                    moveListValide.add(m);
                }
            }
            moveList = moveListValide;
        }
        System.err.println("elpasedTime : " + elapsedTime());


        if (!moveList.isEmpty()) {

            System.err.println("start evaluation " + elapsedTime());
            boolean evaluateCheckMate = false;
            /*if (elapsedTime() < LIMIT_CHECKMATE) {
                System.err.println("active checkmate");
                evaluateCheckMate = true;
            }*/
            Evaluation evaluation = new Evaluation(board, moveList, myColor, myColor, evaluateCheckMate);
            System.err.println("evaluate done");
            Node best = evaluation.getBest();
            if (best != null) {
                System.err.println("take a best " + best.move.move);
            } else {
                System.err.println("no best");
            }
            evaluation.deeper(Constant.DEEPER, myColor);
            best = evaluation.getBest();
            if (best != null) {
                System.err.println("take a best after deeper "+Constant.DEEPER+" " + best.move.move);
            } else {
                System.err.println("no best");
            }
            if (best != null) {
                System.out.println(best.move.move);
                Game.LAST_PIECE = best.move.piece;
            } else {
                System.err.println("No best Move");
                System.out.println("random");
            }


        }

    }

}


