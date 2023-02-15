package com.foxtrot.sudoku.models;

public class Board {

    private final int size;

    private int[][] values;

    public Board(int size) {
        this.size = size;
        values = new int[size][size];
    }

    public void load(int[][] values) {
        this.values = values;
    }

    public int getValue(int row, int col) {
        return values[row][col];
    }

    public void setValue(int row, int col, int value) {
        values[row][col] = value;
    }

    public void validate() {
        // TODO: validate the current board with solution
    }
}
