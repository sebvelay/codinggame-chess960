package com.codinggame.chess.profiling;

import com.codinggame.chess.Constant;
import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.pieces.Color;
import com.codinggame.chess.evaluation.Evaluation;
import com.codinggame.chess.evaluation.Node;

public class Profile {

    public static void main(String[] args) {
        Board board = new Board("nrkbrqbn/pppppppp/8/8/8/8/PPPPPPPP/NRKBRQBN");
        Evaluation evaluation = new Evaluation(board, Color.white,Color.white, Constant.DEEPER);
        Node best = evaluation.getBest();
        System.out.println(best.move.move);
    }
}
