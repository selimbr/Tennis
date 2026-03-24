package fr.tennis.service.states;

import fr.tennis.model.Score;
import fr.tennis.service.rules.TennisRules;

public abstract class GameState {
    public abstract String display(Score score);

    public boolean isTerminal() {
        return false;
    }

    public static GameState resolve(Score score) {
        if (TennisRules.isWin(score)) return WinState.INSTANCE;
        if (TennisRules.isDeuce(score)) return DeuceState.INSTANCE;
        if (TennisRules.isAdvantage(score)) return AdvantageState.INSTANCE;
        return ClassicState.INSTANCE;
    }
}
