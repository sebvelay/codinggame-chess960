package com.codinggame.chess.evaluation;

import com.codinggame.chess.Chrono;
import com.codinggame.chess.Constant;
import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Move;
import com.codinggame.chess.board.Square;
import com.codinggame.chess.board.pieces.Color;
import com.codinggame.chess.board.pieces.Piece;

import java.util.ArrayList;
import java.util.List;


public class Evaluation {
    public List<Node> nodes = new ArrayList<>();
    Color color;
    int totEvaluation = 0;

    public Evaluation(Board board, Color color, Color currentPlayer, int deep) {
        this.color = color;
        for (Move m : board.getMoves(color)) {
            if (isPanic()) {
                System.err.println("panic on create node after : " + Chrono.elapsedTime() + " tot eval = " + totEvaluation);
                return;
            }
            Board newBoard = generateNewBoard(board, m);
            if (isPanic()) {
                System.err.println("panic after generate board : " + Chrono.elapsedTime() + " tot eval = " + totEvaluation);
                return;
            }
            int score = newBoard.getScore();

            //HACK POUR NE PAS DONNER LA QUEEN
            //si on a une piece sur une case controllé, on peut considérer qu'elle va être prise
            Color opponent = color.equals(Color.white) ? Color.black : Color.white;
            List<String> controlledByOpponent = new ArrayList<>();
            for (Piece piece : newBoard.getPieces(opponent)) {
                for (Square s : piece.getControlledSquare(newBoard)) {
                    controlledByOpponent.add(s.translate);
                }
            }
            for (Piece piece : newBoard.getPieces(color)) {
                if (controlledByOpponent.contains(piece.square.translate)) {
                    score = -(piece.getValue() * 1000);
                }
            }

            //FIN HACK


            if (currentPlayer.equals(Color.black)) {
                score = -score;
            }
            if (Constant.DEBUG_EVAL) {
                System.err.println(currentPlayer + " " + m.move + " " + score);
            }
            totEvaluation++;
            nodes.add(new Node(m, score, newBoard, color));

        }
        System.err.println("perform all moves on "+Chrono.elapsedTime());

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
        if (Constant.DEBUG_EVAL) {
            System.err.println(otherColor + " play after " + node.move.move);
        }
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
