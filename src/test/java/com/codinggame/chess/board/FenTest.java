package com.codinggame.chess.board;

import com.codinggame.chess.board.pieces.Bishop;
import com.codinggame.chess.board.pieces.Color;
import com.codinggame.chess.board.pieces.King;
import com.codinggame.chess.board.pieces.Knight;
import com.codinggame.chess.board.pieces.Pawn;
import com.codinggame.chess.board.pieces.Piece;
import com.codinggame.chess.board.pieces.Queen;
import com.codinggame.chess.board.pieces.Rook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class FenTest {

    @Nested
    class TranslatePiece{
        Board board = null;

        @BeforeEach
        void setUp(){
            String fen = "rkrbbnqn/pppppppp/8/8/8/8/PPPPPPPP/RKRBBNQN";
            board = new Board(fen);
        }


        @Test
        void shouldTranslateBlackRook(){
            //r
            Piece piece = board.getPieceFromFenSymbol('r',Square.of(0,0));
            Assertions.assertTrue(piece instanceof Rook);
            Assertions.assertEquals(Color.black,piece.color);
        }

        @Test
        void shouldTranslateWhiteRook(){
            //R
            Piece piece = board.getPieceFromFenSymbol('R',Square.of(0,0));
            Assertions.assertTrue(piece instanceof Rook);
            Assertions.assertEquals(Color.white,piece.color);
        }

        @Test
        void shouldTranslateWhiteBishop(){
            //B
            Piece piece = board.getPieceFromFenSymbol('B',Square.of(0,0));
            Assertions.assertTrue(piece instanceof Bishop);
            Assertions.assertEquals(Color.white,piece.color);
        }

        @Test
        void shouldTranslateBlackBishop(){
            //B
            Piece piece = board.getPieceFromFenSymbol('b',Square.of(0,0));
            Assertions.assertTrue(piece instanceof Bishop);
            Assertions.assertEquals(Color.black,piece.color);
        }

        @Test
        void shouldTranslateBlackPawn(){
            //B
            Piece piece = board.getPieceFromFenSymbol('p',Square.of(0,0));
            Assertions.assertTrue(piece instanceof Pawn);
            Assertions.assertEquals(Color.black,piece.color);
        }

        @Test
        void shouldTranslateWhitePawn(){
            //B
            Piece piece = board.getPieceFromFenSymbol('P',Square.of(0,0));
            Assertions.assertTrue(piece instanceof Pawn);
            Assertions.assertEquals(Color.white,piece.color);
        }

        @Test
        void shouldTranslateBlackKnight(){
            //B
            Piece piece = board.getPieceFromFenSymbol('n',Square.of(0,0));
            Assertions.assertTrue(piece instanceof Knight);
            Assertions.assertEquals(Color.black,piece.color);
        }

        @Test
        void shouldTranslateWhiteKnight(){
            //B
            Piece piece = board.getPieceFromFenSymbol('N',Square.of(0,0));
            Assertions.assertTrue(piece instanceof Knight);
            Assertions.assertEquals(Color.white,piece.color);
        }

        @Test
        void shouldTranslateBlackKing(){
            //k
            Piece piece = board.getPieceFromFenSymbol('k',Square.of(0,0));
            Assertions.assertTrue(piece instanceof King);
            Assertions.assertEquals(Color.black,piece.color);
        }

        @Test
        void shouldTranslateWhiteKing(){
            //K
            Piece piece = board.getPieceFromFenSymbol('K',Square.of(0,0));
            Assertions.assertTrue(piece instanceof King);
            Assertions.assertEquals(Color.white,piece.color);
        }

        @Test
        void shouldTranslateBlackQueen(){
            //q
            Piece piece = board.getPieceFromFenSymbol('q',Square.of(0,0));
            Assertions.assertTrue(piece instanceof Queen);
            Assertions.assertEquals(Color.black,piece.color);
        }

        @Test
        void shouldTranslateWhiteQuen(){
            //Q
            Piece piece = board.getPieceFromFenSymbol('Q',Square.of(0,0));
            Assertions.assertTrue(piece instanceof Queen);
            Assertions.assertEquals(Color.white,piece.color);
        }
    }

    @Nested
    class CreateAllPiece{

        @Test
        void shouldCreate32PiecesAtStart(){
            Board board = new Board("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");

            Assertions.assertEquals(32,board.pieces.size());
        }

    }



}
