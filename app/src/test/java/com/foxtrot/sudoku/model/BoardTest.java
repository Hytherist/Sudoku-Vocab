package com.foxtrot.sudoku.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

    private static final int[][] SUDOKU_QUESTION_4X4 = { { 0, 1, 4, 0 }, { 0, 0, 0, 3 }, { 0, 3, 0, 0 }, { 2, 4, 0, 1 } };

    private static final int[][] SUDOKU_QUESTION_6X6 = {
            { 3, 1, 0, 2, 0, 0 },
            { 0, 6, 0, 0, 1, 0 },
            { 0, 4, 6, 0, 0, 0 },
            { 1, 3, 2, 5, 6, 4 },
            { 0, 2, 0, 0, 5, 1 },
            { 0, 5, 0, 0, 2, 3 },
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
    @Test
    public void testBoardCreation() {
        Board board4x4 = new Board(BoardSize._4X4);
        assertEquals(4, board4x4.getSize());

        Board board6x6 = new Board(BoardSize._6X6);
        assertEquals(6, board6x6.getSize());

        Board board9x9 = new Board(BoardSize._9X9);
        assertEquals(9, board9x9.getSize());

        Board board12x12 = new Board(BoardSize._12X12);
        assertEquals(12, board12x12.getSize());
    }
    Board board4x4 = new Board(BoardSize._4X4);
    Board board6x6 = new Board(BoardSize._6X6);
    Board board9x9 = new Board(BoardSize._9X9);
    Board board12x12 = new Board(BoardSize._12X12);
    @Test
    public void load() {
        board4x4.load(SUDOKU_QUESTION_4X4);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(SUDOKU_QUESTION_4X4[i][j], board4x4.getValue(i, j));
            }
        }

        board6x6.load(SUDOKU_QUESTION_6X6);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                assertEquals(SUDOKU_QUESTION_6X6[i][j], board6x6.getValue(i, j));
            }
        }

        board9x9.load(SUDOKU_QUESTION_9X9);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertEquals(SUDOKU_QUESTION_9X9[i][j], board9x9.getValue(i, j));
            }
        }

        board12x12.load(SUDOKU_QUESTION_12X12);
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                assertEquals(SUDOKU_QUESTION_12X12[i][j], board12x12.getValue(i, j));
            }
        }
    }

    @Test
    public void getValue() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(0, board4x4.getValue(i, j));
            }
        }

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                assertEquals(0, board6x6.getValue(i, j));
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertEquals(0, board9x9.getValue(i, j));
            }
        }

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                assertEquals(0, board12x12.getValue(i, j));
            }
        }
    }

    @Test
    public void setValue() {
        Board board = new Board(BoardSize._4X4);
        board.setValue(0, 0, 10);
        assertEquals(10, board.getValue(0, 0));
    }

    @Test
    public void getSize() {
        assertEquals(4, BoardSize._4X4.getSize());
        assertEquals(6, BoardSize._6X6.getSize());
        assertEquals(9, BoardSize._9X9.getSize());
        assertEquals(12, BoardSize._12X12.getSize());
    }
}
