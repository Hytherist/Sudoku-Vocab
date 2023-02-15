package com.foxtrot.sudoku.models;

import java.util.Map;

/**
 * Top-level driver class that contains the state of the application.
 */
public class App {

    // TODO: load question from DB
    private static int[][] SUDOKU_QUESTION_4X4 = {
            {0, 1, 4, 0},
            {0, 0, 0, 3},
            {0, 3, 0, 0},
            {2, 4, 0, 1},
    };

    // TODO: load solution from DB
    private static int[][] SUDOKU_SOLUTION_4X4 = {
            {3, 1, 4, 2},
            {4, 2, 1, 3},
            {1, 3, 2, 4},
            {2, 4, 3, 1},
    };

    private Board board;

    private Board solution;

    private Map<Integer, Pair<String, String>> wordMap = Map.of(
            1, new Pair<>("Hello", "Bonjour"),
            2, new Pair<>("Goodbye", "Au revoir"),
            3, new Pair<>("Yes", "Oui"),
            4, new Pair<>("No", "Non")
    );

    public App() {}

    public void start() {
        // Initialize board
        board = new Board(4);
        board.load(SUDOKU_QUESTION_4X4);

        solution = new Board(4);
        solution.load(SUDOKU_SOLUTION_4X4);
    }

    public Board getBoard() {
        return board;
    }

    public Board getSolution() {
        return solution;
    }

    public Map<Integer, Pair<String, String>> getWordMap() {
        return wordMap;
    }
}
