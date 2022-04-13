package com.codinggame.chess.profiling;

import com.codinggame.chess.Chrono;
import com.codinggame.chess.Constant;
import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.pieces.Color;
import com.codinggame.chess.evaluation.Evaluation;
import com.codinggame.chess.evaluation.Node;

public class Profile {

    public static void main(String[] args) {
        //do it 1000 times
        for (int i = 0; i < 100; i++) {
            Chrono.start = System.currentTimeMillis() + 1000000;
            Board board = new Board("3qk1b1/r4p2/2p2P2/2P1P3/4Npp1/2K5/1P3NP1/3R4");
            Evaluation evaluation = new Evaluation(board, Color.white,Color.white, Constant.DEEPER);
            Node best = evaluation.getBest();
            System.out.println(best.move.move);
        }

    }
}
