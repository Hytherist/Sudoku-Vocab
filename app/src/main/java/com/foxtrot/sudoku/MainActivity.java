package com.foxtrot.sudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private class Cell {
        int value;
        boolean fixed; // check if word is already given (not able to change)
        Button bt;

        public Cell(int initvalue, Context THIS) {
            value=initvalue;
            if (value!=0) fixed = true;
            else fixed = false;
            bt= new Button(THIS);
            if (fixed) bt.setText(String.valueOf(value));
        }

    }

    Cell[][] table;
    String input;

    TableLayout tl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        input=  "2 9 ? 7 4 3 8 6 1 "+
                "4 ? 1 8 6 5 9 ? 7 "+
                "8 7 6 1 9 2 5 4 3 "+
                "3 8 7 4 5 9 2 1 6 "+
                "6 1 2 3 ? 7 4 ? 5 "+
                "? 4 9 2 ? 6 7 3 8 "+
                "9 2 8 6 7 1 ? 5 4 "+
                "1 5 4 9 3 ? 6 7 2 ";

        String[] split = input.split("");
        table = new Cell[9][9];
        tl = new TableLayout(this);
        for (int i=0; i<9; i++) { // create cell
            TableRow tr= new TableRow(this);
            for (int j=0; j<9; j++) {
                String s=split[i*9+j]; // split the numbers by spaces
                Character c=s.charAt(0); // get the character
                table[i][j]=new Cell(s.charAt(0)=='?'?0:c-'0', this); // puts the character in the cell
                tr.addView(table[i][j].bt);
            }
            tl.addView(tr);
        }
        setContentView(tl);

        // createSudokuGrid();

    }

//    private void createSudokuGrid() {
//        TableLayout table = findViewById(R.id.sudoku_table);
//
//        for (int row = 0; row < 9; row++) {
//            TableRow tableRow = new TableRow(this);
//            tableRow.setLayoutParams(new TableRow.LayoutParams(
//                    TableRow.LayoutParams.MATCH_PARENT,
//                    TableRow.LayoutParams.WRAP_CONTENT
//            ));
//
//            for (int col = 0; col < 9; col++) {
//                TextView cell = new TextView(this);
//                cell.setLayoutParams(new TableRow.LayoutParams(
//                        TableRow.LayoutParams.WRAP_CONTENT,
//                        TableRow.LayoutParams.WRAP_CONTENT
//                ));
//                cell.setGravity(Gravity.CENTER);
//                cell.setText("O");
//                tableRow.addView(cell);
//            }
//            table.addView(tableRow);
//        }
//  }
}