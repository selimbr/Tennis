package fr.tennis.model;

public enum Player {
    A, B;

    public static Player fromChar(char c) {
        if (c == 'A') return A;
        if (c == 'B') return B;
        throw new IllegalArgumentException("Unknown player: " + c);
    }

}
