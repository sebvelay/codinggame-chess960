package com.codinggame.chess.evaluation;

import com.codinggame.chess.board.Move;
import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.pieces.Color;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public final Move move;
    private int score;
    public List<Node> childs;
    public final Board board;
    public final Color colorEvaluated;
    public boolean isEvaluated=false;

    Node(final Move move, Board board, Color color) {
        this.move = move;
        this.board = board;
        this.colorEvaluated = color;
        this.childs = new ArrayList<>();
    }

    Node(int score){
        this.score=score;
        this.board=null;
        this.colorEvaluated=null;
        this.move=null;
        isEvaluated=true;
    }

    private void calculateScore(){
        if(isEvaluated) return;
        int score=board.getScore();
        this.score=score;
        isEvaluated=true;
    }

    public int getScore(){
        calculateScore();
        return score;
    }

    public void setScore(int score) {
        this.score= score;
    }

    public void setChilds(List<Node> nodes) {
        this.childs = nodes;
    }

    public List<Node> getChilds() {
        return childs;
    }
}
