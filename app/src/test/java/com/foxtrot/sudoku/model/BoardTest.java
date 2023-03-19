package com.foxtrot.sudoku.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

    private static final int[][] SUDOKU_QUESTION_4X4 = { { 0, 1, 4, 0 }, { 0, 0, 0, 3 }, { 0, 3, 0, 0 }, { 2, 4, 0, 1 } };

    @Test
    public void testBoardCreation() {
        Board board = new Board(BoardSize._4X4);
        assertEquals(4, board.getSize());
    }

    @Test
    public void load() {
        Board board = new Board(BoardSize._4X4);
        board.load(SUDOKU_QUESTION_4X4);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(SUDOKU_QUESTION_4X4[i][j], board.getValue(i, j));
            }
        }
    }

    @Test
    public void getValue() {
        Board board = new Board(BoardSize._4X4);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(0, board.getValue(i, j));
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

    @Test
    public void getValues() {
        Board board = new Board(BoardSize._4X4);
        board.load(SUDOKU_QUESTION_4X4);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(SUDOKU_QUESTION_4X4[i][j], board.getValues()[i][j]);
            }
        }
    }

    @Test
    public void reset() {
        Board board = new Board(BoardSize._4X4);
        board.load(SUDOKU_QUESTION_4X4);
        board.reset();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(SUDOKU_QUESTION_4X4[i][j], board.getValue(i, j));
            }
        }
    }
}
