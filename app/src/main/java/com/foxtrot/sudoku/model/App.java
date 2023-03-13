package com.foxtrot.sudoku.model;

import java.util.Map;

/** Top-level driver class that contains the state of the application. */
public class App {

    // TODO: load question from DB
    private static int[][] SUDOKU_QUESTION_4X4 = { { 0, 1, 4, 0 }, { 0, 0, 0, 3 }, { 0, 3, 0, 0 }, { 2, 4, 0, 1 } };

    // TODO: load solution from DB
    private static int[][] SUDOKU_SOLUTION_4X4 = { { 3, 1, 4, 2 }, { 4, 2, 1, 3 }, { 1, 3, 2, 4 }, { 2, 4, 3, 1 } };





    private static int[][] SUDOKU_QUESTION_6X6 = { {0, 0, 0, 0, 4, 0 }, { 0, 3, 0, 6, 0, 0 }, { 4, 0, 1, 0, 5, 6 }, {6,5,3,0,1,2}, {1,6,5,2,3,0}, {0,0,2,0,6,0} };

    // TODO: load solution from DB
    private static int[][] SUDOKU_SOLUTION_6X6 =  {  {2, 1, 6, 5, 4, 3 }, { 5, 3, 4, 6, 2, 1 }, { 4, 2, 1, 3, 5, 6 }, {6,5,3,4,1,2}, {1,6,5,2,3,4}, {3,4,2,1, 6,5} };

    private static int[][] SUDOKU_QUESTION_12X12 = { {0, 0, 0, 2, 6, 0, 7, 0, 1 }, { 6, 8, 0, 0, 7, 0, 0, 9, 0 }, { 1, 9, 0, 0, 0, 4, 5, 0, 0 }, { 8, 2, 0, 1, 0, 0, 0, 4, 0 }, {0,0,4,6,0,2,9,0,0}, {0,5,0,0,0,3,0,2,8}, {0,0,9,3,0,0,0,7,4}, {0,4,0,0,5,0,0,3,6},{7,0,3,0,1,8,0,0,0} };

    // TODO: load solution from DB
    private static int[][] SUDOKU_SOLUTION_12X12 =  { {0, 3, 0, 0, 0, 0, 0, 1, 0, 0,0,0 }, {11,0,0,10,0,5,7,0,4,8,0,6 }, { 0,0,0,4,0,3,10,0,1,0,5,0 }, { 0, 0, 10, 0,12,11,0,0,5,3,0,0}, {0,12,0,0,0,2,6,5,7,0,0,11}, {12,0,0,11,6,7,1,0,0,0,9,0}, {0,0,5,6,0,0,11,10,0,2,0,0}, {0,4,0,2,0,12,5,0,6,0,0,0}, {9,0,6,12,0,4,2,0,10,0,0,8},{0,0,0,0,10,0,0,0,0,0,4,0} };
    private static int[][] SUDOKU_QUESTION_9X9 = {
        { 0, 6, 9, 7, 0, 0, 5, 0, 1 },
        { 5, 0, 0, 0, 3, 1, 2, 0, 7 },
        { 1, 2, 7, 6, 8, 5, 4, 9, 3 },
        { 0, 0, 5, 0, 6, 9, 3, 1, 0 },
        { 2, 0, 6, 4, 0, 0, 0, 7, 0 },
        { 9, 0, 0, 0, 0, 7, 0, 5, 4 },
        { 0, 0, 2, 0, 0, 6, 0, 3, 0 },
        { 0, 0, 0, 3, 0, 8, 7, 0, 9 },
        { 8, 0, 3, 5, 0, 4, 0, 0, 6 },
    };

    private static int[][] SUDOKU_SOLUTION_9X9 = {
        { 3, 6, 9, 7, 4, 2, 5, 8, 1 },
        { 5, 8, 4, 9, 3, 1, 2, 6, 7 },
        { 1, 2, 7, 6, 8, 5, 4, 9, 3 },
        { 7, 4, 5, 8, 6, 9, 3, 1, 2 },
        { 2, 1, 6, 4, 5, 3, 9, 7, 8 },
        { 9, 3, 8, 2, 1, 7, 6, 5, 4 },
        { 4, 9, 2, 1, 7, 6, 8, 3, 5 },
        { 6, 5, 1, 3, 2, 8, 7, 4, 9 },
        { 8, 7, 3, 5, 9, 4, 1, 2, 6 },
    };

    private Board board;

    private Board solution;

    private Map<Integer, Pair<String, String>> wordMap = Map.of(
        1,
        new Pair<>("Hello", "Bonjour"),
        2,
        new Pair<>("Goodbye", "Au Revoir"),
        3,
        new Pair<>("Yes", "Oui"),
        4,
        new Pair<>("No", "Non"),
        5,
        new Pair<>("Cat", "Chat"),
        6,
        new Pair<>("Dog", "Chien"),
        7,
        new Pair<>("Strong", "Fort"),
        8,
        new Pair<>("Monde", "World"),
        9,
        new Pair<>("Jour", "Day"),
        10,
        new Pair<>("ii", "lll")

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
                return SUDOKU_QUESTION_6X6;
            case _9X9:
                return SUDOKU_QUESTION_9X9;
            case _12X12:
                return SUDOKU_QUESTION_12X12;
        }
        return null;
    }

    private int[][] getSudokuSolution(BoardSize boardSize) {
        switch (boardSize) {
            case _4X4:
                return SUDOKU_SOLUTION_4X4;
            case _6X6:
                return SUDOKU_SOLUTION_6X6;
            case _9X9:
                return SUDOKU_SOLUTION_9X9;
            case _12X12:
                return SUDOKU_SOLUTION_12X12;
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
