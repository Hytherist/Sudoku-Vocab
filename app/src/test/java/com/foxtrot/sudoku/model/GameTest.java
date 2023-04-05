package com.foxtrot.sudoku.model;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.Objects;
import org.junit.Test;

public class GameTest {

    private static final int[][] SUDOKU_QUESTION_4X4 = { { 0, 1, 4, 0 }, { 0, 0, 0, 3 }, { 0, 3, 0, 0 }, { 2, 4, 0, 1 } };
    private static final int[][] SUDOKU_SOLUTION_4X4 = { { 3, 1, 4, 2 }, { 4, 2, 1, 3 }, { 1, 3, 2, 4 }, { 2, 4, 3, 1 } };
    private final Map<Integer, Pair<String, String>> wordMap = Map.of(
        1,
        new Pair<>("Hello", "Bonjour"),
        2,
        new Pair<>("Goodbye", "Au revoir"),
        3,
        new Pair<>("Yes", "Oui"),
        4,
        new Pair<>("No", "Non")
    );
    Game game = new Game();
    BoardSize boardSize = BoardSize._4X4;

    @Test
    public void start() {
        game.start(boardSize);
        Board board = game.getBoard();
        Board solution = game.getSolution();

        assertEquals(4, board.getSize());
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(SUDOKU_QUESTION_4X4[i][j], board.getValue(i, j));
            }
        }

        assertEquals(4, solution.getSize());
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(SUDOKU_SOLUTION_4X4[i][j], solution.getValue(i, j));
            }
        }
    }

    @Test
    public void getBoard() {
        game.start(boardSize);
        Board board = game.getBoard();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(SUDOKU_QUESTION_4X4[i][j], board.getValue(i, j));
            }
        }
    }

    @Test
    public void getSolution() {
        game.start(boardSize);
        Board solution = game.getSolution();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(SUDOKU_SOLUTION_4X4[i][j], solution.getValue(i, j));
            }
        }
    }

    @Test
    public void getWordMap() {
        Map<Integer, Pair<String, String>> appWordMap = game.getWordMap();
        for (int i = 1; i <= wordMap.size(); i++) {
            assertEquals(Objects.requireNonNull(appWordMap.get(i)).getFirst(), Objects.requireNonNull(wordMap.get(i)).getFirst());
            assertEquals(Objects.requireNonNull(appWordMap.get(i)).getSecond(), Objects.requireNonNull(wordMap.get(i)).getSecond());
        }
    }

    @Test
    public void validate() {
        game.start(boardSize);
        Board board = game.getBoard();
        Board solution = game.getSolution();

        assertFalse(game.validate());

        board.load(SUDOKU_SOLUTION_4X4);
        assertTrue(game.validate());
    }

    @Test
    public void getHintPosition() {
        game.start(boardSize);
        Integer expected = 0;
        assertEquals(expected, game.getHintPosition());

        Board board = game.getBoard();
        board.load(SUDOKU_SOLUTION_4X4);
        assertNull(game.getHintPosition());
    }

    @Test
    public void reset() {
        game.start(boardSize);
        Board board = game.getBoard();
        board.load(SUDOKU_QUESTION_4X4);
        game.reset();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(SUDOKU_QUESTION_4X4[i][j], board.getValue(i, j));
            }
        }
    }
}
