package com.foxtrot.sudoku.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.PopupMenu;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.foxtrot.sudoku.R;
import com.foxtrot.sudoku.models.App;
import com.foxtrot.sudoku.models.Board;
import com.foxtrot.sudoku.models.Pair;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private App app;

    // TODO: ask for user input
    private int boardSize = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create the model
        app = new App();
        app.start();

        // Create the view
        setContentView(R.layout.activity_main);
        displayBoard(app);
    }


    private void displayBoard(App app) {
        TableLayout sudokuGrid = findViewById(R.id.sudoku_table);

        Board board = app.getBoard();
        Map<Integer, Pair<String, String>> wordMap = app.getWordMap();

        for (int i = 0; i < boardSize; i++) {
            TableRow tableRow = new TableRow(this);
            for (int j = 0; j < boardSize; j++) {
                TextView cell = new TextView(this);

                // Set initial word
                int value = board.getValue(i, j);
                Pair<String, String> wordPair = wordMap.get(value);
                boolean empty = wordPair == null;

                Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);

                // Set the bold typeface to the text view
                cell.setTypeface(boldTypeface);

                cell.setText(empty ? "" : wordPair.second);


                if (i < 2) {
                    if (j <= 1) {
                        cell.setBackground(getResources().getDrawable(R.drawable.cellteal));
                    } else if (j >= 2 && j <= 3){
                        cell.setBackground(getResources().getDrawable(R.drawable.cellwhite));
                    }
                } else if (i >= 2 && i <= 3) {
                    if (j <= 1) {
                        cell.setBackground(getResources().getDrawable(R.drawable.cellwhite));
                    }else if (j >= 2 && j <= 3){
                        cell.setBackground(getResources().getDrawable(R.drawable.cellteal));
                    }
                }

//                if (!empty) {
//                    cell.setBackground(getResources().getDrawable(R.drawable.cellinit));
//                } else cell.setBackground(getResources().getDrawable(R.drawable.cellblank));

                // Add click event
                if (empty) {
                    cell.setOnClickListener((view) -> {
                        PopupMenu popupMenu = new PopupMenu(this, view);
                        List<String> words = wordMap.values().stream().map(pair -> pair.first).collect(Collectors.toList());
                        for (String word : words) {
                            popupMenu.getMenu().add(word);
                        }

                        Typeface normalTypeface = Typeface.defaultFromStyle(Typeface.NORMAL);

                        popupMenu.setOnMenuItemClickListener((menuItem) -> {
                            ((TextView) view).setTypeface(normalTypeface);
                            ((TextView) view).setText(menuItem.getTitle());
                            ((TextView) view).setTextColor(Color.BLUE);
                            return true;
                        });
                        popupMenu.show();

                    });
                }

                cell.setGravity(Gravity.CENTER);

                tableRow.addView(cell);
            }
            sudokuGrid.addView(tableRow);
            sudokuGrid.setStretchAllColumns(true);
        }
    }
}