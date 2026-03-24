package fr.tennis.service;

import fr.tennis.model.Player;
import fr.tennis.model.Result;
import fr.tennis.model.Score;
import fr.tennis.service.states.ClassicState;
import fr.tennis.service.states.GameState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameService {
    private static final Logger LOGGER = Logger.getLogger(GameService.class.getName());
    private final Score gameScore = new Score();
    private GameState state;

    public GameService() {
        this.state = ClassicState.INSTANCE;
    }

    public List<Result> play(String input) {
        try {
            validateInput(input);
            return processPoints(input);
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARNING, "Invalid input: " + e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    private List<Result> processPoints(String input) {
        List<Result> results = new ArrayList<>();
        for (char point : input.toCharArray()) {
            if (state.isTerminal()) break;
            gameScore.addPoint(Player.fromChar(point));
            state = GameState.resolve(gameScore);
            results.add(Result.of(state.display(gameScore)));
        }
        return results;
    }

    private void validateInput(String input) throws IllegalArgumentException {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }
        if (!input.matches("[A-B]+")) {
            throw new IllegalArgumentException("Input must contain only A or B in capital letters");
        }
    }

    public GameState getState() {
        return state;
    }
}
