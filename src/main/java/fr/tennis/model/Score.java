package fr.tennis.model;

public class Score {

    private int scoreA = 0;
    private int scoreB = 0;

    public Score(int scoreA, int scoreB) {
        this.scoreA = scoreA;
        this.scoreB = scoreB;
    }

    public Score() {
    }

    public int getScoreA() {
        return scoreA;
    }

    public int getScoreB() {
        return scoreB;
    }

    public void addPoint(Player player) {
        if (player == Player.A) {
            this.scoreA++;
        } else {
            this.scoreB++;
        }
    }

    @Override
    public String toString() {
        return "Score{" +
                "scoreA=" + scoreA +
                ", scoreB=" + scoreB +
                '}';
    }

}
