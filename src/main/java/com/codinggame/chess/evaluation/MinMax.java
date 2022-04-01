package com.codinggame.chess.evaluation;

public class MinMax {

    static int MAXINT = 10000000;

    public static Node miniMax(Node n, int depth, boolean maximizingPlayer) {
        if (depth == 0 || n.getChilds() == null || n.getChilds().isEmpty()) {
            return n;
        }
        if (maximizingPlayer) {
            Node best = new Node(null, -MAXINT, null, null);
            for (Node child : n.getChilds()) {
                best = max(best, miniMax(child, depth - 1, false));
            }
            return best;
        } else {
            Node best = new Node(null, MAXINT, null, null);
            for (Node child : n.getChilds()) {
                best = min(best, miniMax(child, depth - 1, true));
            }
            return best;
        }
    }

    static Node max(Node a, Node b) {
        return a.score >= b.score ? a : b;
    }

    static Node min(Node a, Node b) {
        return a.score <= b.score ? a : b;
    }
}
