package com.codinggame.chess.evaluation;

import com.codinggame.chess.board.pieces.Color;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MinMaxTest {

    @Test
    void shouldTakeMaxOn1Node() {
        List<Node> nodes = new ArrayList<>();
        Node node1 = new Node(null, 3, null, Color.white);
        nodes.add(node1);
        Node node2 = new Node(null, 2, null, Color.white);
        nodes.add(node2);

        Node n = new Node(null,0,null,null);
        n.setChilds(nodes);

        Node max = MinMax.miniMax(n,1,true);

        Assertions.assertEquals(node1, max);
    }
    @Test
    void shouldEvaluateWithMin(){
        //1
        List<Node> nodes = new ArrayList<>();
        Node node1 = new Node(null,3,null,Color.white);
        //1..
        Node node1a = new Node(null,4,null,Color.black);
        Node node1b = new Node(null,5,null,Color.black);
        List<Node> childs1 = new ArrayList<>();
        childs1.add(node1a);
        childs1.add(node1b);
        node1.setChilds(childs1);

        nodes.add(node1);
        //2
        Node node2 = new Node(null,2,null,Color.white);
        nodes.add(node2);
        //2..
        Node node2a = new Node(null,1,null,Color.black);
        Node node2b = new Node(null,6,null,Color.black);
        List<Node> childs2 = new ArrayList<>();
        childs1.add(node2a);
        childs1.add(node2b);
        node2.setChilds(childs2);

        nodes.add(node2);

        Node n = new Node(null,0,null,null);
        n.setChilds(nodes);

        Node max = MinMax.miniMax(n,3,true);

        Assertions.assertEquals(node2, max);
    }


}
