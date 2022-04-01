package com.codinggame.chess.board;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTest {

    @Test
    void shouldTranslatea1(){
        Square square = new Square(7,0);
        String translate = square.translate;
        Assertions.assertEquals(translate,"a1");
    }

    @Test
    void shouldTranslateh8(){
        Square square = new Square(0,7);
        String translate = square.translate;
        Assertions.assertEquals(translate,"h8");
    }

    @Test
    void shouldTranslatea2(){
        Square square = new Square(6,0);
        String translate = square.translate;
        Assertions.assertEquals(translate,"a2");
    }

    @Test
    void shouldConvertIntoSquared6(){
//a1 = 7,0
        //d6 = 2,3
        Square square = new Square("d6");
        Assertions.assertEquals(2,square.row);
        Assertions.assertEquals(3,square.col);
    }

    @Test
    void shouldConvertIntoSquareA1(){
        Square square = new Square("a1");
        Assertions.assertEquals(7,square.row);
        Assertions.assertEquals(0,square.col);
    }
}

