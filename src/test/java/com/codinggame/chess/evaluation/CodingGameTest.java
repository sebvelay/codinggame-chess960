package com.codinggame.chess.evaluation;

import com.codinggame.chess.Game;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class CodingGameTest {

    @Test
    void shouldPlay() {
        Game game = new Game();
        String fen = "rbknqrbn/pppppppp/8/8/8/8/PPPPPPPP/RBKNQRBN";
        String color = "w";
        List<String> legalMoves = Arrays.asList("a2a3", "a2a4", "b2b3", "b2b4", "c2c3", "c2c4", "d1c3", "d1e3", "d2d3", "d2d4", "e2e3", "e2e4", "f2f3", "f2f4", "g2g3", "g2g4", "h1g3", "h2h3", "h2h4");

        game.play(fen, color, legalMoves);
    }


    @Test
    void bug1(){
        Game game = new Game();
        String fen = "bbrkqn1r/p1pppppp/5n2/p7/8/8/1PPPPPPP/BBRKQNNR";
        String color = "w";
        List<String> legalMoves = Arrays.asList("b1a2","b2b3","b2b4","c2c3","c2c4","d1c1","d2d3","d2d4","e2e3","e2e4","f1e3","f1g3","f2f3","f2f4","g1f3","g1h3","g2g3","g2g4","h2h3","h2h4");
        game.play(fen, color, legalMoves);

    }

    @Test
    void bugBadEvaluation(){
        Game game = new Game();
        String fen = "qrnkrbbn/pPppp3/8/6p1/6P1/5p1p/1PPPPP1P/QRNKRBBN";
        String color = "w";
        List<String> legalMoves = Arrays.asList("a1a2","a1a3","a1a4","a1a5","a1a6","a1a7","b2b3","b2b4","b7a8n","b7a8b","b7a8r","b7a8q","b7c8n","b7c8b","b7c8r","b7c8q","c1a2","c1b3","c1d3","c2c3","c2c4","d2d3","d2d4","e2e3","e2e4","e2f3","f1g2","f1h3","h1g3");
        game.play(fen, color, legalMoves);
    }

    @Test
    void bugBadEvaluationBlack(){
        Game game = new Game();
        String fen = "nbr1nkb1/2pp1pp1/pp5p/1q6/8/1PPBPP2/P4BPP/N1RQNK1R";
        String color = "b";

        game.play(fen,color,null);

    }

    @Test
    void bugE4(){
        Game game = new Game();
        String fen = "rkrbq1bn/ppp1pppp/3p2n1/8/P7/6P1/1PPPPP1P/RKRBQNBN";
        String color = "w";

        //"a1a2","a1a3","a4a5","b1a2","b2b3","b2b4","c2c3","c2c4","d2d3","d2d4","e2e3","e2e4","f1e3","f2f3","f2f4","g3g4","h2h3","h2h4"`
        List<String> legalMoves = Arrays.asList("a1a2","a1a3","a4a5","b1a2","b2b3","b2b4","c2c3","c2c4","d2d3","d2d4","e2e3","e2e4","f1e3","f2f3","f2f4","g3g4","h2h3","h2h4");

        game.play(fen,color,legalMoves);
    }

}
