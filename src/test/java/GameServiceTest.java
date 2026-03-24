import fr.tennis.model.Player;
import fr.tennis.model.Result;
import fr.tennis.service.GameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameServiceTest {

    @ParameterizedTest
    @ValueSource(strings = {"AC", "A1", "abc", "a b"})
    void invalidInputWrongCharacterThrowsException(String input) {
        // GIVEN
        GameService service = new GameService();
        // WHEN
        List<Result> out = service.play(input);
        // THEN
        assertTrue(out.isEmpty());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void invalidInputNullOrEmptyThrowsException(String input) {
        // GIVEN
        GameService service = new GameService();
        // WHEN
        List<Result> out = service.play(input);
        // THEN
        assertTrue(out.isEmpty());
    }

    @Test
    void play_bothPlayersScoreOnePoint() {
        // GIVEN
        GameService service = new GameService();
        List<Result> expected = List.of(
                Result.of(playersResultClassic("15", "0")),
                Result.of(playersResultClassic("15", "15"))
        );
        // WHEN
        List<Result> results = service.play("AB");
        // THEN
        assertIterableEquals(expected, results);
    }

    @Test
    void play_advantagePlayerA() {
        // GIVEN
        GameService service = new GameService();
        List<Result> expected = List.of(
                Result.of(playersResultClassic("15", "0")),
                Result.of(playersResultClassic("30", "0")),
                Result.of(playersResultClassic("40", "0")),
                Result.of(playersResultClassic("40", "15")),
                Result.of(playersResultClassic("40", "30")),
                Result.of(playersResultDeuce()),
                Result.of(advantagePlayer(Player.A))
        );
        // WHEN
        List<Result> results = service.play("AAABBBA");
        // THEN
        assertIterableEquals(expected, results);
    }

    @Test
    void play_fourPointsWinsA() {
        // GIVEN
        String input = "AAAA";
        GameService service = new GameService();
        List<Result> expected = List.of(
                Result.of(playersResultClassic("15", "0")),
                Result.of(playersResultClassic("30", "0")),
                Result.of(playersResultClassic("40", "0")),
                Result.of(winPlayer(Player.A))
        );
        // WHEN
        List<Result> results = service.play(input);
        // THEN
        assertIterableEquals(expected, results);
        assertTrue(service.getState().isTerminal());
    }

    @Test
    void play_fourPointsWinsB() {
        // GIVEN
        String input = "BBBB";
        GameService service = new GameService();
        List<Result> expected = List.of(
                Result.of(playersResultClassic("0", "15")),
                Result.of(playersResultClassic("0", "30")),
                Result.of(playersResultClassic("0", "40")),
                Result.of(winPlayer(Player.B))
        );
        // WHEN
        List<Result> results = service.play(input);
        // THEN
        assertIterableEquals(expected, results);
        assertTrue(service.getState().isTerminal());
    }

    @Test
    void play_deuceSequence() {
        // GIVEN
        String input = "AAABBB";
        GameService service = new GameService();
        List<Result> expected = List.of(
                Result.of(playersResultClassic("15", "0")),
                Result.of(playersResultClassic("30", "0")),
                Result.of(playersResultClassic("40", "0")),
                Result.of(playersResultClassic("40", "15")),
                Result.of(playersResultClassic("40", "30")),
                Result.of(playersResultDeuce())
        );
        // WHEN
        List<Result> results = service.play(input);
        // THEN
        assertIterableEquals(expected, results);
        assertFalse(service.getState().isTerminal());
    }

    @Test
    void play_advantageThenWinA() {
        // GIVEN
        String input = "AAABBBAA";
        GameService service = new GameService();
        List<Result> expected = List.of(
                Result.of(playersResultClassic("15", "0")),
                Result.of(playersResultClassic("30", "0")),
                Result.of(playersResultClassic("40", "0")),
                Result.of(playersResultClassic("40", "15")),
                Result.of(playersResultClassic("40", "30")),
                Result.of(playersResultDeuce()),
                Result.of(advantagePlayer(Player.A)),
                Result.of(winPlayer(Player.A))
        );
        // WHEN
        List<Result> results = service.play(input);

        // THEN
        assertIterableEquals(expected, results);
        assertTrue(service.getState().isTerminal());
    }

    @Test
    void play_extraPointsAfterWinAreIgnored() {
        // GIVEN
        String input = "AAAAAA";
        GameService service = new GameService();
        List<Result> expected = List.of(
                Result.of(playersResultClassic("15", "0")),
                Result.of(playersResultClassic("30", "0")),
                Result.of(playersResultClassic("40", "0")),
                Result.of(winPlayer(Player.A))
        );
        // WHEN
        List<Result> results = service.play(input);
        // THEN
        assertIterableEquals(expected, results);
        assertTrue(service.getState().isTerminal());
    }

    private String playersResultClassic(String scoreA, String scoreB) {
        return "Player A: " + scoreA + " / Player B: " + scoreB;
    }

    private String playersResultDeuce() {
        return "Deuce";
    }

    private String advantagePlayer(Player player) {
        return "Advantage Player: " + player;
    }

    private String winPlayer(Player player) {
        return "Player: " + player + " wins the game!";
    }

}
