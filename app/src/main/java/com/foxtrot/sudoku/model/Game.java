package com.foxtrot.sudoku.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/** Top-level driver class that contains the state of the application. */
public class Game {

    private static final int[][] SUDOKU_QUESTION_4X4 = { { 0, 1, 4, 0 }, { 0, 0, 0, 3 }, { 0, 3, 0, 0 }, { 2, 4, 0, 1 } };

    private static final int[][] SUDOKU_SOLUTION_4X4 = { { 3, 1, 4, 2 }, { 4, 2, 1, 3 }, { 1, 3, 2, 4 }, { 2, 4, 3, 1 } };

    private static final int[][] SUDOKU_QUESTION_6X6 = {
        { 3, 1, 0, 2, 0, 0 },
        { 0, 6, 0, 0, 1, 0 },
        { 0, 4, 6, 0, 0, 0 },
        { 1, 3, 2, 5, 6, 4 },
        { 0, 2, 0, 0, 5, 1 },
        { 0, 5, 0, 0, 2, 3 },
    };

    private static final int[][] SUDOKU_SOLUTION_6X6 = {
        { 3, 1, 5, 2, 4, 6 },
        { 2, 6, 4, 3, 1, 5 },
        { 5, 4, 6, 1, 3, 2 },
        { 1, 3, 2, 5, 6, 4 },
        { 4, 2, 3, 6, 5, 1 },
        { 6, 5, 1, 4, 2, 3 },
    };

    private static final int[][] SUDOKU_QUESTION_9X9 = {
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

    private static final int[][] SUDOKU_SOLUTION_9X9 = {
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

    private static final int[][] SUDOKU_QUESTION_12X12 = {
        { 0, 0, 1, 0, 6, 0, 2, 0, 10, 7, 0, 0 },
        { 6, 2, 0, 5, 11, 0, 0, 0, 1, 0, 0, 0 },
        { 0, 8, 0, 0, 0, 0, 12, 3, 11, 0, 6, 2 },
        { 10, 9, 7, 0, 2, 0, 6, 0, 0, 11, 5, 0 },
        { 0, 5, 0, 0, 0, 0, 0, 0, 0, 3, 7, 12 },
        { 3, 11, 0, 0, 0, 0, 0, 7, 0, 0, 0, 9 },
        { 5, 7, 11, 0, 4, 0, 0, 0, 0, 0, 0, 1 },
        { 8, 0, 0, 0, 0, 0, 0, 10, 0, 9, 0, 0 },
        { 0, 4, 0, 0, 9, 7, 0, 0, 0, 6, 0, 0 },
        { 0, 1, 12, 0, 0, 0, 0, 0, 0, 0, 0, 11 },
        { 4, 0, 0, 0, 0, 0, 0, 2, 0, 12, 0, 0 },
        { 0, 0, 0, 0, 5, 12, 0, 0, 7, 1, 2, 6 },
    };

    private static final int[][] SUDOKU_SOLUTION_12X12 = {
        { 12, 3, 1, 11, 6, 4, 2, 5, 10, 7, 9, 8 },
        { 6, 2, 10, 5, 11, 9, 7, 8, 1, 4, 12, 3 },
        { 7, 8, 9, 4, 10, 1, 12, 3, 11, 5, 6, 2 },
        { 10, 9, 7, 12, 2, 3, 6, 1, 8, 11, 5, 4 },
        { 1, 5, 4, 6, 8, 11, 10, 9, 2, 3, 7, 12 },
        { 3, 11, 2, 8, 12, 5, 4, 7, 6, 10, 1, 9 },
        { 5, 7, 11, 9, 4, 6, 8, 12, 3, 2, 10, 1 },
        { 8, 12, 6, 1, 3, 2, 5, 10, 4, 9, 11, 7 },
        { 2, 4, 3, 10, 9, 7, 1, 11, 12, 6, 8, 5 },
        { 9, 1, 12, 2, 7, 10, 3, 6, 5, 8, 4, 11 },
        { 4, 6, 5, 7, 1, 8, 11, 2, 9, 12, 3, 10 },
        { 11, 10, 8, 3, 5, 12, 9, 4, 7, 1, 2, 6 },
    };

    private static final List<Pair<String, String>> WORD_PAIR_LIST = List.of(
        new Pair<>("Hello", "Bonjour"),
        new Pair<>("Goodbye", "Au Revoir"),
        new Pair<>("Yes", "Oui"),
        new Pair<>("No", "Non"),
        new Pair<>("Cat", "Chat"),
        new Pair<>("Dog", "Chien"),
        new Pair<>("Strong", "Fort"),
        new Pair<>("Monde", "World"),
        new Pair<>("Jour", "Day"),
        new Pair<>("Tall", "Grand"),
        new Pair<>("Small", "Petit"),
        new Pair<>("Quiet", "Calme")
    );

    private static final Random RANDOM = new Random();

    private BoardSize boardSize;

    private Board board;

    private Board question;

    private Board solution;

    private Map<Integer, Pair<String, String>> wordMap;

    public Game() {}

    public void start(BoardSize boardSize) {
        this.boardSize = boardSize;

        board = new Board(boardSize);
        board.load(getSudokuQuestion(boardSize));

        question = new Board(boardSize);
        question.load(getSudokuQuestion(boardSize));

        solution = new Board(boardSize);
        solution.load(getSudokuSolution(boardSize));

        loadWordMap();
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

    private void loadWordMap() {
        wordMap = new HashMap<>();

        List<Pair<String, String>> wordPairList = new ArrayList<>(WORD_PAIR_LIST);
        int size = board.getSize();
        for (int i = 1; i <= size; i++) {
            Pair<String, String> wordPair = wordPairList.remove(RANDOM.nextInt(wordPairList.size()));
            wordMap.put(i, wordPair);
        }
    }

    public Board getBoard() {
        return board;
    }

    public Board getQuestion() {
        return question;
    }

    public Board getSolution() {
        return solution;
    }

    public Map<Integer, Pair<String, String>> getWordMap() {
        return wordMap;
    }

    public boolean validate() {
        int size = board.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
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

    public void reset() {
        board = new Board(boardSize);
        board.load(getSudokuQuestion(boardSize));
        loadWordMap();
    }
}
