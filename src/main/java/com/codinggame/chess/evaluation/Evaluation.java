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

public class Evaluation extends Chrono {
    List<Node> nodes = new ArrayList<>();
    Color color;

    public Evaluation(Board board, List<Move> moves, Color color, Color currentPlayer, boolean evaluateCheckMate) {
        this.color = color;
        for (Move m : moves) {

            if (this.elapsedTime() > MAX_TIME_EVALUATION) {
                System.err.println("panic on Evaluation" + this.elapsedTime());
                return;
            }

            //je simule le nouveau board
            Board newBoard = board.clone();
            if (m.isTakePiece) {
                newBoard.takePiece(m);
            } else {
                newBoard.move(m);
            }
            //je l'évalue

            int score = newBoard.getScore();

            if (currentPlayer.equals(Color.black)) {
                score = -score;
            }


            //je créé un node
            Node node = new Node(m, score, newBoard, color);

            //on regarde si on est checkmate
            //1 l'adversaire doit être check
            Color opponent = Color.black;
            if (color.equals(Color.black)) {
                opponent = Color.white;
            }

            if (newBoard.isCheck(opponent)) {

                node.move.isCheck = true;
                if (evaluateCheckMate) {
                    //2 si l'adversaire est check, on regarde ces moves, si il est toujours check il est checkmate
                    List<Move> movesOppenent = newBoard.getMoves(opponent);
                    boolean checkMate = true;
                    for (Move mo : movesOppenent) {
                        //je simule le nouveau board
                        Board newBoardOppent = newBoard.clone();
                        if (mo.isTakePiece) {
                            newBoardOppent.takePiece(mo);
                        } else {
                            newBoardOppent.move(mo);
                        }
                        //si on trouve un move pas check on est pas checkmate
                        if (!newBoardOppent.isCheck(opponent)) {
                            checkMate = false;
                            break;
                        }
                    }
                    if (checkMate) {
                        node.score = 1000000000;
                        this.nodes.clear();
                        this.nodes.add(node);
                        break;
                    }
                }
            }
            this.nodes.add(node);
            if (Constant.DEBUG_EVAL) {
                System.err.println(node.move.move + " " + node.score);
            }
        }
        orderNodes();

    }

    public void orderNodes() {
        Collections.sort(this.nodes, new Comparator<Node>() {
            @Override
            public int compare(final Node o1, final Node o2) {
                return Boolean.compare(o2.move.isTakePiece, o1.move.isTakePiece);
            }
        });
    }

    public void deeper(int depth, Color player) {
        if (this.elapsedTime() > MAX_TIME_EVALUATION) {
            System.err.println("panic 1 on deeper at " + this.elapsedTime());
            return;
        }

        if (depth == 0) {
            return;
        }
        for (Node n : this.nodes) {
            if (n.checkMate) {
                return;
            }
        }

        for (Node n : this.nodes) {
            if (Constant.DEBUG_EVAL) {
                System.err.println("nodes for moves : " + n.move.move);
            }
            if (this.elapsedTime() > MAX_TIME_EVALUATION) {
                System.err.println("panic on deeper at " + this.elapsedTime());
                return;
            }
            Board boardInPosition = n.board;
            Color colorToEvaluate = Color.white;
            if (n.colorEvaluated.equals(Color.white)) {
                colorToEvaluate = Color.black;
            }

            List<Move> moves = boardInPosition.getMoves(colorToEvaluate);
            //List<Piece> pieces = boardInPosition.getPieces(colorToEvaluate);
            /*for (Piece p : pieces) {
                moves.addAll(p.legalsMove(boardInPosition));
            }*/
            /**
             * hack pour enlever les cas ou l'adversaire ne prend pas
             */
            moves.removeIf(m -> !m.isTakePiece);


            boolean activeCheckMate = false;
            /*if (elapsedTime() < LIMIT_CHECKMATE) {
                activeCheckMate = true;
            }*/

            Evaluation evaluation = new Evaluation(boardInPosition, moves, colorToEvaluate, player, activeCheckMate);

            n.setChilds(evaluation.nodes);
            evaluation.deeper(depth - 1, player);

        }

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
