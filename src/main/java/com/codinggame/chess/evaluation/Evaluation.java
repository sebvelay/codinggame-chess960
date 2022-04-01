package com.codinggame.chess.evaluation;

import com.codinggame.chess.Chrono;
import com.codinggame.chess.Constant;
import com.codinggame.chess.board.Move;
import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.pieces.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Evaluation extends Chrono {
    List<Node> nodes = new ArrayList<>();
    Color color;
    int totEvaluation=0;

    public Evaluation(Board board, Color color, Color currentPlayer, int deep) {
        this.color = color;
        List<Node> nodes = board.getMoves(color)
                .parallelStream()
                .map(m -> {
                    Board newBoard = generateNewBoard(board, m);
                    int score = newBoard.getScore();
                    if (currentPlayer.equals(Color.black)) {
                        score = -score;
                    }
                    if (Constant.DEBUG_EVAL) {
                        System.err.println(currentPlayer + " " + m.move + " " + score);
                    }
                    totEvaluation++;
                    return new Node(m, score, newBoard, color);
                })
                .collect(Collectors.toList());
        this.nodes = nodes;
        for (Node node : nodes) {
            if (isPanic()) {
                System.err.println("panic on Evaluation" + this.elapsedTime()+" tot eval = "+totEvaluation);
                return;
            }
            deep(deep, node, color);
        }
        System.err.println("Finish evaluation at "+this.elapsedTime()+" for "+this.totEvaluation+" evaluation");
    }

    private boolean isPanic() {
            return this.elapsedTime() > MAX_TIME_EVALUATION;

    }

    private void deep(int depth, Node node, Color color) {
        if(depth==0){
            return;
        }
        if (isPanic()) {
            System.err.println("panic on deeper" + this.elapsedTime()+" tot eval = "+totEvaluation);
            return;
        }

        Color otherColor = color.equals(Color.white) ? Color.black : Color.white;
        if (Constant.DEBUG_EVAL) {
            System.err.println(otherColor + " play after " + node.move.move);
        }
        List<Node> nodes = node.board.getMoves(otherColor)
                .parallelStream()
                .filter(m -> m.isTakePiece)
                .map(m -> {
                    Board newBoard = generateNewBoard(node.board, m);
                    int score = newBoard.getScore();
                    if (this.color == Color.black) {
                        score = -score;
                    }
                    if (Constant.DEBUG_EVAL) {
                        System.err.println(otherColor + " " + m.move + " " + score);
                    }
                    totEvaluation++;
                    return new Node(m, score, newBoard, otherColor);
                })
                .collect(Collectors.toList());
        node.setChilds(nodes);
        for(Node n : nodes){
            deep(depth-1,n,otherColor);
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

    public void orderNodes() {
        Collections.sort(this.nodes, new Comparator<Node>() {
            @Override
            public int compare(final Node o1, final Node o2) {
                return Boolean.compare(o2.move.isTakePiece, o1.move.isTakePiece);
            }
        });
    }


    public Node getBest() {
        if (nodes.isEmpty()) {
            return null;
        }
        Node parent = new Node(null, 0, null, null);
        parent.setChilds(this.nodes);
        Node best = null;
        for (Node n : this.nodes) {
            if (this.elapsedTime() > MAX_TIME_GETBEST) {
                System.err.println("panic on getBest at " + this.elapsedTime());
                return best;
            }
            Node node = MinMax.miniMax(n, Constant.DEEPER + 1, false);
            if (best == null
                    || node.score > best.score
                    //|| (node.score == best.score && n.move.isTakePiece)
                    || (node.score == best.score && n.move.isCheck)
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
