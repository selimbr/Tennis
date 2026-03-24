package fr.tennis.service.rules;

import fr.tennis.model.Score;

public class TennisRules {
    public static String convertScore(int score) {
        return switch (score) {
            case 1 -> "15";
            case 2 -> "30";
            case 3 -> "40";
            default -> "0";
        };
    }

    public static boolean isWin(Score score) {
        int a = score.getScoreA();
        int b = score.getScoreB();
        return (a >= 4 || b >= 4) && Math.abs(a - b) >= 2;
    }

    public static boolean isDeuce(Score score) {
        int a = score.getScoreA();
        int b = score.getScoreB();
        return a >= 3 && a == b;
    }

    public static boolean isAdvantage(Score score) {
        int a = score.getScoreA();
        int b = score.getScoreB();
        return (a >= 4 || b >= 4) && Math.abs(a - b) == 1;
    }
}
