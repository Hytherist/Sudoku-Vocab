package com.foxtrot.sudoku.model;

import java.util.Arrays;

public class Board {

    private final int size;

    private int[][] values;

    public Board(BoardSize boardSize) {

        if (boardSize == null) {
            throw new IllegalArgumentException("Board size cannot be null.");
        }

        this.size = boardSize.getSize();
        values = new int[size][size];
    }

    public void load(int[][] values) {
        this.values = copy(values);
    }

    public int getValue(int row, int col) {
        return values[row][col];
    }

    public void setValue(int row, int col, int value) {
        values[row][col] = value;
    }

    public int getSize() {
        return size;
    }

    private int[][] copy(int[][] src) {
        return Arrays.stream(src).map(int[]::clone).toArray(e -> src.clone());
    }
}
