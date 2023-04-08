package com.foxtrot.sudoku.model;

import static org.junit.Assert.*;


import java.util.Optional;

import org.junit.Test;

public class GameTest {

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
    Game game = new Game();

    @Test
    public void start() {
        game.start(BoardSize._4X4);
        assertEquals(4, game.getBoard().getSize());

        game.start(BoardSize._6X6);
        assertEquals(6, game.getBoard().getSize());

        game.start(BoardSize._9X9);
        assertEquals(9, game.getBoard().getSize());

        game.start(BoardSize._12X12);
        assertEquals(12, game.getBoard().getSize());
    }

    @Test
    public void getBoard() {
        game.start(BoardSize._4X4);
        Board board4x4 = game.getBoard();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(SUDOKU_QUESTION_4X4[i][j], board4x4.getValue(i, j));
            }
        }

        game.start(BoardSize._6X6);
        Board board6x6 = game.getBoard();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                assertEquals(SUDOKU_QUESTION_6X6[i][j], board6x6.getValue(i, j));
            }
        }

        game.start(BoardSize._9X9);
        Board board9x9 = game.getBoard();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertEquals(SUDOKU_QUESTION_9X9[i][j], board9x9.getValue(i, j));
            }
        }

        game.start(BoardSize._12X12);
        Board board12x12 = game.getBoard();

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                assertEquals(SUDOKU_QUESTION_12X12[i][j], board12x12.getValue(i, j));
            }
        }
    }

    @Test
    public void getQuestion() {
        game.start(BoardSize._4X4);
        Board question4x4 = game.getQuestion();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(SUDOKU_QUESTION_4X4[i][j], question4x4.getValue(i, j));
            }
        }

        game.start(BoardSize._6X6);
        Board question6x6 = game.getQuestion();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                assertEquals(SUDOKU_QUESTION_6X6[i][j], question6x6.getValue(i, j));
            }
        }

        game.start(BoardSize._9X9);
        Board question9x9 = game.getQuestion();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertEquals(SUDOKU_QUESTION_9X9[i][j], question9x9.getValue(i, j));
            }
        }

        game.start(BoardSize._12X12);
        Board question12x12 = game.getQuestion();

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                assertEquals(SUDOKU_QUESTION_12X12[i][j], question12x12.getValue(i, j));
            }
        }
    }

    @Test
    public void getSolution() {
        game.start(BoardSize._4X4);
        Board solution4x4 = game.getSolution();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(SUDOKU_SOLUTION_4X4[i][j], solution4x4.getValue(i, j));
            }
        }

        game.start(BoardSize._6X6);
        Board solution6x6 = game.getSolution();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                assertEquals(SUDOKU_SOLUTION_6X6[i][j], solution6x6.getValue(i, j));
            }
        }

        game.start(BoardSize._9X9);
        Board solution9x9 = game.getSolution();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertEquals(SUDOKU_SOLUTION_9X9[i][j], solution9x9.getValue(i, j));
            }
        }

        game.start(BoardSize._12X12);
        Board solution12x12 = game.getSolution();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                assertEquals(SUDOKU_SOLUTION_12X12[i][j], solution12x12.getValue(i, j));
            }
        }
    }

    @Test
    public void getWordMap() {
//        Map<Integer, Pair<String, String>> appWordMap = game.getWordMap();
//        for (int i = 1; i <= wordMap.size(); i++) {
//            assertEquals(Objects.requireNonNull(appWordMap.get(i)).getFirst(), Objects.requireNonNull(wordMap.get(i)).getFirst());
//            assertEquals(Objects.requireNonNull(appWordMap.get(i)).getSecond(), Objects.requireNonNull(wordMap.get(i)).getSecond());
//        }

    }

    @Test
    public void validate() {
        game.start(BoardSize._4X4);
        Board board4x4 = game.getBoard();
        assertFalse(game.validate());
        board4x4.load(SUDOKU_SOLUTION_4X4);
        assertTrue(game.validate());

        game.start(BoardSize._6X6);
        Board board6x6 = game.getBoard();
        assertFalse(game.validate());
        board6x6.load(SUDOKU_SOLUTION_6X6);
        assertTrue(game.validate());

        game.start(BoardSize._9X9);
        Board board9x9 = game.getBoard();
        assertFalse(game.validate());
        board9x9.load(SUDOKU_SOLUTION_9X9);
        assertTrue(game.validate());

        game.start(BoardSize._12X12);
        Board board12x12 = game.getBoard();
        assertFalse(game.validate());
        board12x12.load(SUDOKU_SOLUTION_12X12);
        assertTrue(game.validate());
    }

    @Test
    public void getHintPosition() {
        game.start(BoardSize._4X4);
        Board board = game.getBoard();
        board.load(SUDOKU_SOLUTION_4X4);
        assertEquals(Optional.empty(), game.getHintPosition());
    }

    @Test
    public void reset() {
        game.start(BoardSize._4X4);
        Board board4x4 = game.getBoard();
        board4x4.load(SUDOKU_QUESTION_4X4);
        game.reset();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(SUDOKU_QUESTION_4X4[i][j], board4x4.getValue(i, j));
            }
        }

        game.start(BoardSize._6X6);
        Board board6x6 = game.getBoard();
        board6x6.load(SUDOKU_QUESTION_6X6);
        game.reset();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                assertEquals(SUDOKU_QUESTION_6X6[i][j], board6x6.getValue(i, j));
            }
        }

        game.start(BoardSize._9X9);
        Board board9x9 = game.getBoard();
        board9x9.load(SUDOKU_QUESTION_9X9);
        game.reset();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertEquals(SUDOKU_QUESTION_9X9[i][j], board9x9.getValue(i, j));
            }
        }

        game.start(BoardSize._12X12);
        Board board12x12 = game.getBoard();
        board9x9.load(SUDOKU_QUESTION_12X12);
        game.reset();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                assertEquals(SUDOKU_QUESTION_12X12[i][j], board12x12.getValue(i, j));
            }
        }
    }
}
