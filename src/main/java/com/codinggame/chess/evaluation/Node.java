package com.codinggame.chess.evaluation;

import com.codinggame.chess.board.Move;
import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.pieces.Color;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public final Move move;
    public int score;
    public List<Node> childs;
    public final Board board;
    public final Color colorEvaluated;

    Node(final Move move, int score, Board board, Color color) {
        this.move = move;
        this.score = score;
        this.board = board;
        this.colorEvaluated = color;
        this.childs = new ArrayList<>();
    }

    public void setChilds(List<Node> nodes) {
        this.childs = nodes;
    }

    public List<Node> getChilds() {
        return childs;
    }
}
