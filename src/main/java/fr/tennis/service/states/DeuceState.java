package fr.tennis.service.states;

import fr.tennis.model.Score;

public class DeuceState extends GameState {

    public static final DeuceState INSTANCE = new DeuceState();

    @Override
    public String display(Score score) {
        return "Deuce";
    }
}
