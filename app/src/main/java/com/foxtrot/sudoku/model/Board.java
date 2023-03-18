package com.foxtrot.sudoku.model;

public class Board {

    private final int size;

    private int[][] values;
    private int[][] initialValues;

    public Board(BoardSize boardSize) {
        this.size = boardSize.getSize();
        values = new int[size][size];
        initialValues = new int[size][size];
    }

    public void load(int[][] values) {
        this.values = values;
        this.initialValues = cValues(values);
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

    public int[][] getValues() {
        return this.values;
    }

    public void reset() {
        this.values = cValues(initialValues);
    }

    private int[][] cValues(int[][] source) {
        int[][] dest = new int[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(source[i], 0, dest[i], 0, size);
        }
        return dest;
    }

}
