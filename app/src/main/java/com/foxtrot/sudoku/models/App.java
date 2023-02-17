package com.foxtrot.sudoku.models;

import java.util.Map;

/** Top-level driver class that contains the state of the application. */
public class App {

    // TODO: load question from DB
    private static int[][] SUDOKU_QUESTION_4X4 = { { 0, 1, 4, 0 }, { 0, 0, 0, 3 }, { 0, 3, 0, 0 }, { 2, 4, 0, 1 } };

    // TODO: load solution from DB
    private static int[][] SUDOKU_SOLUTION_4X4 = { { 3, 1, 4, 2 }, { 4, 2, 1, 3 }, { 1, 3, 2, 4 }, { 2, 4, 3, 1 } };

    private Board board;

    private Board solution;

    private Map<Integer, Pair<String, String>> wordMap = Map.of(
        1,
        new Pair<>("Hello", "Bonjour"),
        2,
        new Pair<>("Goodbye", "Au revoir"),
        3,
        new Pair<>("Yes", "Oui"),
        4,
        new Pair<>("No", "Non")
    );

    public App() {}

    public void start(BoardSize boardSize) {
        board = new Board(boardSize);
        board.load(getSudokuQuestion(boardSize));

        solution = new Board(boardSize);
        solution.load(getSudokuSolution(boardSize));
    }

    private int[][] getSudokuQuestion(BoardSize boardSize) {
        switch (boardSize) {
            case _4X4:
                return SUDOKU_QUESTION_4X4;
            case _6X6:
            case _9X9:
            case _12X12:
                throw new UnsupportedOperationException();
        }
        return null;
    }

    private int[][] getSudokuSolution(BoardSize boardSize) {
        switch (boardSize) {
            case _4X4:
                return SUDOKU_SOLUTION_4X4;
            case _6X6:
            case _9X9:
            case _12X12:
                throw new UnsupportedOperationException();
        }
        return null;
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

    public boolean validate() {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getValue(i, j) != solution.getValue(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
}
