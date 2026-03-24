package fr.tennis.service.states;

import fr.tennis.model.Score;
import fr.tennis.service.rules.TennisRules;

public class ClassicState extends GameState {

    public static final ClassicState INSTANCE = new ClassicState();

    @Override
    public String display(Score score) {
        String scoreA = TennisRules.convertScore(score.getScoreA());
        String scoreB = TennisRules.convertScore(score.getScoreB());
        return "Player A: " + scoreA + " / Player B: " + scoreB;
    }

}
