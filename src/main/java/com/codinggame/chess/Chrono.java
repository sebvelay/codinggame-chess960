package com.codinggame.chess;

public class Chrono {

    public static final long MAX_TIME_EVALUATION = 40;
    public static final long MAX_TIME_GETBEST = 48;

    public static long start;

    public static long elapsedTime() {
        return System.currentTimeMillis() - start;
    }
}
