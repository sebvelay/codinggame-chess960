package com.codinggame.chess;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int constantsCount = in.nextInt();
        for (int i = 0; i < constantsCount; i++) {
            String name = in.next();
            String value = in.next();
            System.err.println(name + " " + value);
        }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println("fen moves");

        int totRound = 0;

        // game loop
        while (true) {


            String fen = in.next();
            String color = in.next();
            String castling = in.next();
            String enPassant = in.next();
            int halfMoveClock = in.nextInt();
            int fullMove = in.nextInt();

            System.err.println("fen " + fen);
            System.err.println("color " + color);
            //System.err.println("castling "+castling);
            //System.err.println("coenPassantlor "+enPassant);
            //System.err.println("halfMoveClock "+halfMoveClock);
            //System.err.println("fullMove "+fullMove);

            int movesNum = in.nextInt();
            System.err.println("moves" + movesNum);
            ArrayList<String> legalMovesFromGame = new ArrayList<>();
            for (int i = 0; i < movesNum; i++) {
                String move = in.next();
                legalMovesFromGame.add(move);
            }
            for (int i = 0; i < movesNum; i++) {
                System.err.print("\"" + legalMovesFromGame.get(i) + "\",");
            }
            System.err.println("");
            long start = System.currentTimeMillis();
            Chrono.start = start;


            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");
            Game game = new Game();
            game.play(fen, color, legalMovesFromGame);
        }
    }
}
