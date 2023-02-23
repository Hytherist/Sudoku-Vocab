package com.foxtrot.sudoku.models;

import static org.junit.Assert.*;

import org.junit.Test;

public class PairTest {

    @Test
    public void testPairTestCreation() {
        Pair<String, String> pair = new Pair<>("Hello", "Bonjour");
        assertEquals("Hello", pair.getFirst());
        assertEquals("Bonjour", pair.getSecond());
    }
}
