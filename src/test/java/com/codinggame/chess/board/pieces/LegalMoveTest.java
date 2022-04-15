package com.codinggame.chess.board.pieces;

import com.codinggame.chess.board.Board;
import com.codinggame.chess.board.Move;
import com.codinggame.chess.board.Square;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LegalMoveTest {

    Board board;

    @Nested
    class PawnTest {
        @Test
        void legalMoveForBlackPawnInitPos() {
            board = new Board("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
            Piece piece = board.getPiece(Square.of(1, 0));

            List<Square> moves = piece.legalsMove(board,Square.of(1, 0)).stream().map(m -> m.target).collect(Collectors.toList());

            List<Square> expectedLegal = Arrays.asList(Square.of(2, 0), Square.of(3, 0));
            Assertions.assertTrue(moves.containsAll(expectedLegal));
        }

        @Test
        void legalMoveForWhitePawnInitPos() {
            board = new Board("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
            Piece piece = board.getPiece(Square.of(6, 0));

            List<Square> moves = piece.legalsMove(board,Square.of(6, 0)).stream().map(m -> m.target).collect(Collectors.toList());

            List<Square> expectedLegal = Arrays.asList(Square.of(5, 0), Square.of(4, 0));
            Assertions.assertTrue(moves.containsAll(expectedLegal));
        }

        @Test
        void legalMoveForWithePawn2SquareAfter() {
            board = new Board("rnbqkbnr/ppp1pppp/8/8/3p4/8/PPPPPPPP/RNBQKBNR");
            Piece piece = board.getPiece(Square.of(6, 3));

            List<Square> moves = piece.legalsMove(board,Square.of(6, 3)).stream().map(m -> m.target).collect(Collectors.toList());

            List<Square> expectedLegal = Arrays.asList(Square.of(5, 3));
            Assertions.assertTrue(moves.containsAll(expectedLegal));
            Assertions.assertEquals(1, moves.size());
        }

        @Test
        void legalMoveForWithePawn1SquareAfter() {
            board = new Board("rnbqkbnr/ppp1pppp/8/8/8/3p4/PPPPPPPP/RNBQKBNR");
            Piece piece = board.getPiece(Square.of(6, 3));

            List<Square> moves = piece.legalsMove(board,Square.of(6, 3)).stream().map(m -> m.target).collect(Collectors.toList());

            Assertions.assertEquals(0, moves.size());
        }

        @Test
        void legalMoveForBlackPawn2SquareAfter() {
            board = new Board("rnbqkbnr/pppppppp/8/3P4/8/8/PPP1PPPP/RNBQKBNR");
            Piece piece = board.getPiece(Square.of(1, 3));

            List<Square> moves = piece.legalsMove(board,Square.of(1, 3)).stream().map(m -> m.target).collect(Collectors.toList());

            List<Square> expectedLegal = Arrays.asList(Square.of(2, 3));
            Assertions.assertTrue(moves.containsAll(expectedLegal));
            Assertions.assertEquals(1, moves.size());
        }

        @Test
        void legalMoveForBlackPawn1SquareAfter() {
            board = new Board("rnbqkbnr/pppppppp/3P4/8/8/8/PPP1PPPP/RNBQKBNR");
            Piece piece = board.getPiece(Square.of(1, 3));

            List<Square> moves = piece.legalsMove(board,Square.of(1, 3)).stream().map(m -> m.target).collect(Collectors.toList());

            Assertions.assertEquals(0, moves.size());
        }

        @Test
        void legalMoveForWhiteCanTakeRight() {
            board = new Board("8/8/8/3p4/2P5/8/8/8");

            Piece piece = board.getPiece(Square.of("c4"));

            List<String> moves = piece.legalsMove(board,Square.of("c4")).stream().map(m->m.move).collect(Collectors.toList());

            List<String> expectedLegal = Arrays.asList("c4d5","c4c5");
            Assertions.assertTrue(moves.containsAll(expectedLegal));
        }

        @Test
        void legalMoveForWhiteCanTakeLeft() {
            board = new Board("8/8/1p6/2P5/8/8/8/8");

            Piece piece = board.getPiece(Square.of("c5"));

            List<String> moves = piece.legalsMove(board,Square.of("c5")).stream().map(m->m.move).collect(Collectors.toList());

            List<String> expectedLegal = Arrays.asList("c5b6","c5c6");
            Assertions.assertTrue(moves.containsAll(expectedLegal));
        }

        @Test
        void legalMoveForWhiteNoMove1() {
            board = new Board("8/8/p7/P7/8/8/8/8");
            Piece piece = board.getPiece(Square.of(3, 0));

            List<Move> moves = piece.legalsMove(board,Square.of(3, 0));

            Assertions.assertEquals(0, moves.size());
        }

        @Test
        void legalMoveForWhiteNoMove2() {
            board = new Board("8/8/7p/7P/8/8/8/8");
            Piece piece = board.getPiece(Square.of(3, 7));

            List<Move> moves = piece.legalsMove(board,Square.of(3, 7));

            Assertions.assertEquals(0, moves.size());
        }

    }


}
