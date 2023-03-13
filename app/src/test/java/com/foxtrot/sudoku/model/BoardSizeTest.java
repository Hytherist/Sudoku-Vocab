package com.foxtrot.sudoku.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardSizeTest {

    @Test
    public void getSize() {
        assertEquals(4, BoardSize._4X4.getSize());
        assertEquals(6, BoardSize._6X6.getSize());
        assertEquals(9, BoardSize._9X9.getSize());
        assertEquals(12, BoardSize._12X12.getSize());
    }

    @Test
    public void getGridRowSize() {
        assertEquals(2, BoardSize._4X4.getGridRowSize());
        assertEquals(2, BoardSize._6X6.getGridRowSize());
        assertEquals(3, BoardSize._9X9.getGridRowSize());
        assertEquals(4, BoardSize._12X12.getGridRowSize());
    }

    @Test
    public void getGridColSize() {
        assertEquals(2, BoardSize._4X4.getGridColSize());
        assertEquals(3, BoardSize._6X6.getGridColSize());
        assertEquals(3, BoardSize._9X9.getGridColSize());
        assertEquals(4, BoardSize._12X12.getGridColSize());
    }
}
