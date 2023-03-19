package com.foxtrot.sudoku.model;

import android.widget.TextView;

import com.foxtrot.sudoku.controller.MainMenuActivity;

import java.text.CollationElementIterator;
import java.util.Map;
import java.util.Stack;


/** Top-level driver class that contains the state of the application. */
public class App {

    // TODO: load question from DB
    private static int[][] SUDOKU_QUESTION_4X4 = { { 0, 1, 4, 0 }, { 0, 0, 0, 3 }, { 0, 3, 0, 0 }, { 2, 4, 0, 1 } };

    // TODO: load solution from DB
    private static int[][] SUDOKU_SOLUTION_4X4 = { { 3, 1, 4, 2 }, { 4, 2, 1, 3 }, { 1, 3, 2, 4 }, { 2, 4, 3, 1 } };


    private static int[][] SUDOKU_QUESTION_6X6 = { {0, 0, 0, 0, 4, 0 }, { 0, 3, 0, 6, 0, 0 }, { 4, 0, 1, 0, 5, 6 }, {6,5,3,0,1,2}, {1,6,5,2,3,0}, {0,0,2,0,6,0} };

    // TODO: load solution from DB
    private static int[][] SUDOKU_SOLUTION_6X6 =  {  {2, 1, 6, 5, 4, 3 }, { 5, 3, 4, 6, 2, 1 }, { 4, 2, 1, 3, 5, 6 }, {6,5,3,4,1,2}, {1,6,5,2,3,4}, {3,4,2,1, 6,5} };

    private static int[][] SUDOKU_QUESTION_12X12 = {
            {0, 3, 8, 0, 0, 10, 7, 0, 0, 6,4,0 },
            {4,7,0,11,0,0,0,0,10,0,8,5 },
            { 0,0,6,9,0,0,0,0,12,7,0,0 },
            { 0, 0, 0, 0,0,0,0,0,0,0,0,0},
            {0,1,11,5,3,0,0,12,8,4,2,0},
            {0,10,0,0,4,0,0,5,0,0,12,0},
            {0,12,0,0,2,0,0,3,0,0,9,0},
            {0,2,10,4,6,0,0,8,1,12,3,0},
            {0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,3,12,0,0,0,0,4,11,0,0},
            {5,8,0,10, 0, 0, 0, 0, 9, 0,1,0 },
            {0, 6, 1, 0, 0, 9, 5, 0, 0,10,7,0} } ;

    // TODO: load solution from DB
    private static int[][] SUDOKU_SOLUTION_12X12 =  {

                    {9, 3, 8, 2, 5, 10, 7, 1, 11, 6, 4, 12},
                    {4, 7, 12, 11, 1, 6, 2, 3, 10, 5, 8, 5},
                    {11, 5, 6, 9, 8, 3, 4, 2, 12, 7, 1, 10},
                    {3, 4, 2, 1, 12, 11, 10, 9, 5, 8, 7, 6},
                    {10, 1, 11, 5, 3, 7, 9, 12, 8, 4, 2, 6},
                    {7, 10, 9, 6, 4, 8, 3, 5, 2, 1, 12, 11},
                    {8, 12, 5, 7, 2, 4, 1, 3, 6, 10, 9, 11},
                    {12, 2, 10, 4, 6, 1, 11, 8, 1, 12, 3, 9},
                    {6, 11, 1, 8, 7, 9, 5, 10, 3, 2, 12, 4},
                    {1, 9, 3, 12, 10, 2, 8, 6, 4, 11, 5, 7},
                    {5, 8, 4, 10, 11, 12, 6, 7, 9, 3, 1, 2},
                    {2, 6, 1, 3, 9, 5, 12, 4, 7, 10, 7, 8}
            };
    private static int[][] SUDOKU_QUESTION_9X9 = {
            { 0, 0, 0, 2, 6, 0, 7, 0, 1 },
            { 6, 8, 0, 0, 7, 0, 0, 9, 0},
            { 1, 9, 0, 0, 0, 4, 5, 0, 0 },
            { 8, 2, 0, 0, 0, 0, 0, 4, 0 },
            { 0, 0, 4, 6, 0, 2, 9, 0, 0 },
            { 0, 5, 0, 0, 0, 3, 0, 2, 8 },
            { 0, 0, 9, 3, 0, 0, 0, 7, 4 },
            { 0, 4, 0, 0, 5, 0, 0, 3, 6 },
            { 7, 0, 3, 0, 1, 8, 0, 0, 0 },
    };

    private static int[][] SUDOKU_SOLUTION_9X9 = {
        { 4, 3, 5, 2, 6, 9, 7, 8, 1 },
        { 6, 8, 2, 5, 7, 1, 4, 9, 3 },
        { 1, 9, 7, 8, 3, 4, 5, 6, 2 },
        { 8, 2, 6, 1, 9, 5, 3, 4, 7 },
        { 3, 7, 4, 6, 8, 2, 9, 1, 5 },
        { 9, 5, 1, 7, 4, 3, 6, 2, 8 },
        { 5, 1, 9, 3, 2, 6, 8, 7, 4 },
        { 2, 4, 8, 9, 5, 7, 1, 3, 6 },
        { 7, 6, 3, 4, 1, 8, 2, 5, 9 }
    };





    private Board board;

    private Board solution;

    private Map<Integer, Pair<String, String>> wordMap = Map.ofEntries(
        Map.entry(1,
        new Pair<>("Hello", "Bonjour")),
        Map.entry(2,
        new Pair<>("Goodbye", "Au Revoir")),
        Map.entry(3,
        new Pair<>("Yes", "Oui")),
        Map.entry(4,
        new Pair<>("No", "Non")),
        Map.entry(5,
        new Pair<>("Cat", "Chat")),
        Map.entry(6,
        new Pair<>("Dog", "Chien")),
        Map.entry(7,
        new Pair<>("Strong", "Fort")),
        Map.entry(8,
        new Pair<>("Monde", "World")),
        Map.entry(9,
        new Pair<>("Jour", "Day")),
        Map.entry(10,
        new Pair<>("Eau", "Water")),
        Map.entry(11,
        new Pair<>("Thé", "Tea")),
        Map.entry(12,
        new Pair<>("Avion", "Plane"))
    );


    public App() {}

    public void start(BoardSize boardSize) {

        board = new Board(boardSize);
        board.load(getSudokuQuestion(boardSize));

        solution = new Board(boardSize);
        solution.load(getSudokuSolution(boardSize));
    }

    private int[][] getSudokuQuestion(BoardSize boardSize) {
        switch (boardSize) {
            case _4X4:
                return SUDOKU_QUESTION_4X4;
            case _6X6:
                return SUDOKU_QUESTION_6X6;
            case _9X9:
                return SUDOKU_QUESTION_9X9;
            case _12X12:
                return SUDOKU_QUESTION_12X12;
        }
        return null;
    }

    private int[][] getSudokuSolution(BoardSize boardSize) {
        switch (boardSize) {
            case _4X4:
                return SUDOKU_SOLUTION_4X4;
            case _6X6:
                return SUDOKU_SOLUTION_6X6;
            case _9X9:
                return SUDOKU_SOLUTION_9X9;
            case _12X12:
                return SUDOKU_SOLUTION_12X12;
        }
        return null;
    }

    public Board getBoard() {
        return board;
    }

    public Board getSolution() {
        return solution;
    }

    public Map<Integer, Pair<String, String>> getWordMap() {
        return wordMap;
    }

    public boolean validate() {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getValue(i, j) != solution.getValue(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public Integer getHintPosition() {
        int boxSize = (int) Math.sqrt(board.getSize());
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getValue(i, j) != solution.getValue(i, j)) {
                    int boxRow = i / boxSize;
                    int boxCol = j / boxSize;
                    return boxRow * boxSize + boxCol;
                }
            }
        }
        return null;
    }


}
