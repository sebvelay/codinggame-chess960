package com.codinggame.chess.evaluation;

import com.codinggame.chess.Game;

public class MinMax {

    static int MAXINT = 10000000;

    public static Node miniMax(Node n, int depth, boolean maximizingPlayer) {
        if (depth == 0 || n.getChilds() == null || n.getChilds().isEmpty()) {
            return n;
        }

        maximizingPlayer= !(Game.currentPlayer==n.colorEvaluated);
        /*if(Game.currentPlayer== Color.black){
            maximizingPlayer= !maximizingPlayer;
        }*/

        if (maximizingPlayer) {
            Node best = new Node(-MAXINT);
            for (Node child : n.getChilds()) {


                best = max(best, miniMax(child, depth - 1, false));
            }
            return best;
        } else {
            Node best = new Node(MAXINT);
            for (Node child : n.getChilds()) {


                best = min(best, miniMax(child, depth - 1, true));
            }
            return best;
        }
    }

    static Node max(Node a, Node b) {
        return a.getScore() >= b.getScore() ? a : b;
    }

    static Node min(Node a, Node b) {
        return a.getScore() <= b.getScore() ? a : b;
    }
}
