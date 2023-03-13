package com.foxtrot.sudoku.model;

public class Board {

    private final int size;

    private int[][] values;

    public Board(BoardSize boardSize) {
        this.size = boardSize.getSize();
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

    public int getSize() {
        return size;
    }
}
