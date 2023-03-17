package com.foxtrot.sudoku.model;

import android.widget.TextView;

import com.foxtrot.sudoku.controller.MainActivity;

import java.text.CollationElementIterator;
import java.util.Map;
import java.util.Stack;

/** Top-level driver class that contains the state of the application. */
public class App {

    // TODO: Load question and solutions from DB
    private static int[][] SUDOKU_QUESTION_4X4 = { { 0, 1, 4, 0 }, { 0, 0, 0, 3 }, { 0, 3, 0, 0 }, { 2, 4, 0, 1 } };

    private static int[][] SUDOKU_SOLUTION_4X4 = { { 3, 1, 4, 2 }, { 4, 2, 1, 3 }, { 1, 3, 2, 4 }, { 2, 4, 3, 1 } };

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
            case _9X9:
                return SUDOKU_QUESTION_9X9;
            default:
                throw new UnsupportedOperationException();
        }
    }

    private int[][] getSudokuSolution(BoardSize boardSize) {
        switch (boardSize) {
            case _4X4:
                return SUDOKU_SOLUTION_4X4;
            case _9X9:
                return SUDOKU_SOLUTION_9X9;
            default:
                throw new UnsupportedOperationException();
        }
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

    public Integer getHintPosition() {
        int boxSize = (int) Math.sqrt(board.getSize());
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getValue(i, j) != solution.getValue(i, j)) {
                    int boxRow = i / boxSize;
                    int boxCol = j / boxSize;
                    return boxRow * boxSize + boxCol;
                }
            }
        }
        return null;
    }


}
