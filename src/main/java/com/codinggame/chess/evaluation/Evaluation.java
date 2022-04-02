package com.codinggame.chess.evaluation;

import com.codinggame.chess.Chrono;
import com.codinggame.chess.Constant;
import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Move;
import com.codinggame.chess.board.pieces.Color;

import java.util.ArrayList;
import java.util.List;


public class Evaluation {
    public List<Node> nodes;
    Color color;
    int totEvaluation = 0;

    public Evaluation(Board board, Color color, Color currentPlayer, int deep) {
        this.color = color;
        List<Node> nodes = new ArrayList<>();
        for (Move m : board.getMoves(color)) {
            System.err.println("new node " + Chrono.elapsedTime());
            Board newBoard = generateNewBoard(board, m);
            int score = newBoard.getScore();
            if (currentPlayer.equals(Color.black)) {
                score = -score;
            }
            if (Constant.DEBUG_EVAL) {
                System.err.println(currentPlayer + " " + m.move + " " + score);
            }
            totEvaluation++;
            nodes.add(new Node(m, score, newBoard, color));
        }

        this.nodes = nodes;
        for (Node node : nodes) {
            if (isPanic()) {
                System.err.println("panic on Evaluation" + Chrono.elapsedTime() + " tot eval = " + totEvaluation);
                return;
            }
            deep(deep, node, color);
        }
        System.err.println("Finish evaluation at " + Chrono.elapsedTime() + " for " + this.totEvaluation + " evaluation");
    }

    private boolean isPanic() {
        return Chrono.elapsedTime() > Chrono.MAX_TIME_EVALUATION;

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
        if (Constant.DEBUG_EVAL) {
            System.err.println(otherColor + " play after " + node.move.move);
        }
        List<Node> nodes = new ArrayList<>();
        for (Move m : node.board.getMoves(otherColor)) {
            if (m.isTakePiece) {

                Board newBoard = generateNewBoard(node.board, m);
                int score = newBoard.getScore();
                if (this.color == Color.black) {
                    score = -score;
                }
                if (Constant.DEBUG_EVAL) {
                    System.err.println(otherColor + " " + m.move + " " + score);
                }
                totEvaluation++;
                nodes.add(new Node(m, score, newBoard, otherColor));
            }

        }
        node.setChilds(nodes);
        for (Node n : nodes) {
            deep(depth - 1, n, otherColor);
        }

    }

    private Board generateNewBoard(final Board board, final Move m) {
        Board newBoard = null;
        if (m.isTakePiece) {
            newBoard = board.takePiece(m);
        } else {
            newBoard = board.move(m);
        }
        return newBoard;
    }

    public Node getBest() {
        if (nodes.isEmpty()) {
            return null;
        }
        Node parent = new Node(null, 0, null, null);
        parent.setChilds(this.nodes);
        Node best = null;
        for (Node n : this.nodes) {
            if (Chrono.elapsedTime() > Chrono.MAX_TIME_GETBEST) {
                System.err.println("panic on getBest at " + Chrono.elapsedTime());
                if (best == null) {
                    best = n;
                }
                return best;
            }
            Node node = MinMax.miniMax(n, Constant.DEEPER + 1, false);
            if (best == null
                    || node.score > best.score
                //|| (node.score == best.score && n.move.isTakePiece)
                //|| (node.score == best.score && n.move.isCheck)
                //|| (node.score == best.score && n.move.piece instanceof Pawn)
                //|| (node.score == best.score && Game.LAST_PIECE != null && (!Game.LAST_PIECE.getClass().equals(node.move.piece.getClass())))
            ) {
                best = n;
                best.score = node.score;
            }

        }
        return best;
    }
}
