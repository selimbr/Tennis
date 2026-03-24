package fr.tennis;

import fr.tennis.service.GameService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameService gameService = new GameService();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Start the game by typing a sequence: ");

        do {
            String input = scanner.nextLine();

            gameService.play(input).forEach(r -> System.out.println(r.getResult()));
        }
        while (!(gameService.getState().isTerminal()));
    }
}