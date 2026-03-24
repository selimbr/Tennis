package fr.tennis.model;

public record Result(String result) {

    public static Result of(String message) {
        return new Result(message);
    }

    public String getResult() {
        return result;
    }

    @Override
    public String toString() {
        return result;
    }
}
