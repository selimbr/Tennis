package fr.tennis.service.states;

import fr.tennis.model.Score;

public class WinState extends GameState {

    public static final WinState INSTANCE = new WinState();

    @Override
    public String display(Score score) {
        String winner = score.getScoreA() > score.getScoreB() ? "A" : "B";
        return "Player: " + winner + " wins the game!";
    }

    @Override
    public boolean isTerminal() {
        return true;
    }
}
