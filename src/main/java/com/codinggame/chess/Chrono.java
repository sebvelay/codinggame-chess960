package com.codinggame.chess;

public class Chrono {

    public static final long MAX_TIME_EVALUATION = 43;
    public static final long MAX_TIME_GETBEST = 50;
    public static final long LIMIT_CHECKMATE = 10;

    public static long start;

    public static long elapsedTime() {
        return System.currentTimeMillis() - start;
    }
}
