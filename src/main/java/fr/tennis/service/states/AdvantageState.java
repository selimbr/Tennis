package fr.tennis.service.states;

import fr.tennis.model.Score;

public class AdvantageState extends GameState {

    public static final AdvantageState INSTANCE = new AdvantageState();

    @Override
    public String display(Score score) {
        String player = score.getScoreA() > score.getScoreB() ? "A" : "B";
        return "Advantage Player: " + player;
    }
}
