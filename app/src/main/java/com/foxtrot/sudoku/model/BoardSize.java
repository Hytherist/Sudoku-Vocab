package com.foxtrot.sudoku.model;

public enum BoardSize {
    _4X4(4, 2, 2),
    _6X6(6, 2, 3),
    _9X9(9, 3, 3),
    _12X12(12, 3, 4);

    private final int size;
    private final int gridRowSize;
    private final int gridColSize;

    BoardSize(int size, int gridRowSize, int gridColSize) {
        this.size = size;
        this.gridRowSize = gridRowSize;
        this.gridColSize = gridColSize;
    }

    public int getSize() {
        return size;
    }

    public int getGridRowSize() {
        return gridRowSize;
    }

    public int getGridColSize() {
        return gridColSize;
    }
}
