import fr.tennis.model.Score;
import fr.tennis.service.rules.TennisRules;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TennisRulesTest {

    @Test
    void convertScore_basicValues() {
        assertEquals("0", TennisRules.convertScore(0));
        assertEquals("15", TennisRules.convertScore(1));
        assertEquals("30", TennisRules.convertScore(2));
        assertEquals("40", TennisRules.convertScore(3));
    }

    @Test
    void isWin_simpleWinA() {
        Score s = new Score(4, 1);
        assertTrue(TennisRules.isWin(s));
        assertFalse(TennisRules.isDeuce(s));
        assertFalse(TennisRules.isAdvantage(s));
    }

    @Test
    void isWin_requiresTwoPointDifference() {
        Score s = new Score(4, 3);
        assertFalse(TennisRules.isWin(s));
        assertFalse(TennisRules.isDeuce(s));
        assertTrue(TennisRules.isAdvantage(s));
    }

    @Test
    void deuce_when_scores_equal_and_at_least_three() {
        Score s = new Score(3, 3);
        assertTrue(TennisRules.isDeuce(s));
        assertFalse(TennisRules.isWin(s));
        assertFalse(TennisRules.isAdvantage(s));

        s = new Score(10, 10);
        assertTrue(TennisRules.isDeuce(s));
    }

    @Test
    void advantage_detected_correctly_and_then_win() {
        Score s = new Score(5, 4);
        assertTrue(TennisRules.isAdvantage(s));
        assertFalse(TennisRules.isDeuce(s));
        assertFalse(TennisRules.isWin(s));

        s = new Score(6, 4);
        assertTrue(TennisRules.isWin(s));
    }

    @Test
    void advantage_for_b_player() {
        Score s = new Score(4, 5);
        assertTrue(TennisRules.isAdvantage(s));
        assertFalse(TennisRules.isWin(s));

        s = new Score(4, 6);
        assertTrue(TennisRules.isWin(s));
    }

    @Test
    void isWin_b_when_a_less_than_4() {
        // B has 4 and A has 2 -> B wins and this forces the right side of (a>=4 || b>=4)
        Score s = new Score(2, 4);
        assertTrue(TennisRules.isWin(s));
    }

    @Test
    void isWin_false_when_difference_less_than_two_for_b() {
        Score s = new Score(3, 4);
        assertFalse(TennisRules.isWin(s));
        assertTrue(TennisRules.isAdvantage(s));
    }
}
