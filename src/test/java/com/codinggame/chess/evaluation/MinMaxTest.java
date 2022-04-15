package com.codinggame.chess.evaluation;

import com.codinggame.chess.board.pieces.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class MinMaxTest {

    @Test
    void shouldTakeMaxOn1Node() {
        List<Node> nodes = new ArrayList<>();
        Node node1 = new Node(3);
        nodes.add(node1);
        Node node2 = new Node(2);
        nodes.add(node2);

        Node n = new Node(0);
        n.setChilds(nodes);

        Node max = MinMax.miniMax(n, 1, true);

        Assertions.assertEquals(node1, max);
    }

    @Test
    void shouldEvaluateWithMin() {
        //1
        List<Node> nodes = new ArrayList<>();
        Node node1 = new Node(3);
        //1..
        Node node1a = new Node(-4);
        Node node1b = new Node(-5);
        List<Node> childs1 = new ArrayList<>();
        childs1.add(node1a);
        childs1.add(node1b);
        node1.setChilds(childs1);

        nodes.add(node1);
        //2
        Node node2 = new Node(2);
        nodes.add(node2);
        //2..
        Node node2a = new Node(-1);
        Node node2b = new Node(-6);
        List<Node> childs2 = new ArrayList<>();
        childs1.add(node2a);
        childs1.add(node2b);
        node2.setChilds(childs2);

        nodes.add(node2);

        Node n = new Node(0);
        n.setChilds(nodes);

        Node max = MinMax.miniMax(n, 3, true);

        Assertions.assertEquals(node2, max);
    }


}
