package com.example.hwk01;

public class Score {
    static int score = 0;

    public static void up() {
        score++;
    }

    public static void reset() {
        score = 0;
    }

    public static int getScore() {
        return score;
    }
}
