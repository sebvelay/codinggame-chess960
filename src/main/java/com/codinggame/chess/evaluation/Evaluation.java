package com.codinggame.chess.evaluation;

import com.codinggame.chess.Chrono;
import com.codinggame.chess.Constant;
import com.codinggame.chess.Game;
import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Move;
import com.codinggame.chess.board.pieces.Color;

import java.util.ArrayList;
import java.util.List;


public class Evaluation {
    public List<Node> nodes = new ArrayList<>();
    Color color;
    int totEvaluation = 0;

    public Evaluation(Board board, Color color, Color currentPlayer, int deep) {
        this.color = color;
        List<Move> moves = board.getMoves(color);

        for (Move m : moves) {
            if (isPanic()) {
                System.err.println("panic on create node after : " + Chrono.elapsedTime() + " tot eval = " + totEvaluation);
                return;
            }
            Board newBoard = generateNewBoard(board, m);
            if (isPanic()) {
                System.err.println("panic after generate board : " + Chrono.elapsedTime() + " tot eval = " + totEvaluation);
                return;
            }
            totEvaluation++;
            nodes.add(new Node(m, newBoard, color));

        }
        System.err.println("perform all moves on " + Chrono.elapsedTime());

        for (Node node : nodes) {
            if (isPanic()) {
                System.err.println("panic on Evaluation deeper" + Chrono.elapsedTime() + " tot eval = " + totEvaluation);
                return;
            }
            deep(deep, node, color);
        }
        System.err.println("Finish evaluation at " + Chrono.elapsedTime() + " for " + this.totEvaluation + " evaluation");
    }

    private void deep(int depth, Node node, Color color) {
        if (depth == 0) {
            return;
        }
        if (isPanic()) {
            System.err.println("panic on deeper" + Chrono.elapsedTime() + " tot eval = " + totEvaluation);
            return;
        }

        Color otherColor = color.equals(Color.white) ? Color.black : Color.white;
        List<Node> nodes = new ArrayList<>();
        for (Move m : node.board.getMoves(otherColor)) {
            if (isPanic()) {
                System.err.println("panic on deep, on move loop : " + Chrono.elapsedTime() + " tot eval = " + totEvaluation);
                return;
            }
            if (m.isTakePiece) {

                Board newBoard = generateNewBoard(node.board, m);
                if (isPanic()) {
                    System.err.println("panic after generate board on deep : " + Chrono.elapsedTime() + " tot eval = " + totEvaluation);
                    return;
                }
                totEvaluation++;
                Node n = new Node(m, newBoard, otherColor);
                nodes.add(n);
            }

        }
        node.setChilds(nodes);
        for (Node n : nodes) {
            if (isPanic()) {
                System.err.println("panic on deeper + 1 " + Chrono.elapsedTime() + " tot eval = " + totEvaluation);
                return;
            }
            deep(depth - 1, n, otherColor);
        }

    }

    private boolean isPanic() {
        return Chrono.elapsedTime() > Chrono.MAX_TIME_EVALUATION;

    }


    private Board generateNewBoard(final Board board, final Move m) {
        return board.move(m);
    }

    public Node getBest() {
        if (nodes.isEmpty()) {
            return null;
        }
        Node parent = new Node(null, null, null);
        parent.setChilds(this.nodes);
        if (Constant.DEBUG_EVAL) {
            System.err.println("tree before minmax");

            debugNode(parent,0);
        }
        Node best = null;
        for (Node n : this.nodes) {
            if (Chrono.elapsedTime() > Chrono.MAX_TIME_GETBEST) {
                System.err.println("panic on getBest at " + Chrono.elapsedTime());
                if (best == null) {
                    best = n;
                }
                return best;
            }
            boolean maximizingPlayer = n.colorEvaluated == Game.currentPlayer;

            Node node = MinMax.miniMax(n, Constant.DEEPER + 1, maximizingPlayer);

            if (best == null
                    || node.getScore() > best.getScore()
                    || (node.getScore() == best.getScore() && n.move.isTakePiece)
                //|| (node.score == best.score && n.move.isCheck)
                //|| (node.score == best.score && n.move.piece instanceof Pawn)
                //|| (node.score == best.score && Game.LAST_PIECE != null && (!Game.LAST_PIECE.getClass().equals(node.move.piece.getClass())))
            ) {
                best = n;
                best.setScore(node.getScore());
            }

        }
        if (Constant.DEBUG_EVAL) {
            System.err.println("final tree");
            debugNode(parent,0);
        }

        return best;
    }

    private void debugNode(Node n,int deep) {
        if (n.move != null) {
            String level = "";
            if(deep==1){
                level="|";
            }else{
                level=" ";
            }
            for (int i = 0; i < deep; i++) {
                level += "_";
            }
            System.err.println(level+" ("+n.colorEvaluated+" "+(deep)+") "+n.move.move + " : " + n.getScore());
        }
        for (Node node : n.getChilds()) {
            debugNode(node,deep+1);
        }

    }
}
