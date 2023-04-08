package com.foxtrot.sudoku.model;

public enum BoardSize {
    _4X4(4, 2, 2, 18),
    _6X6(6, 2, 3, 12),
    _9X9(9, 3, 3, 8),
    _12X12(12, 3, 4, 6);

    private final int size;
    private final int gridRowSize;
    private final int gridColSize;

    private final int textSize;

    BoardSize(int size, int gridRowSize, int gridColSize, int textSize) {
        this.size = size;
        this.gridRowSize = gridRowSize;
        this.gridColSize = gridColSize;
        this.textSize = textSize;
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

    public int getTextSize() {
        return textSize;
    }
}
