package com.foxtrot.sudoku.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
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
                cell.setText(empty ? "" : wordPair.second);

                // Add click event
                if (empty) {
                    cell.setOnClickListener((view) -> {
                        PopupMenu popupMenu = new PopupMenu(this, view);
                        List<String> words = wordMap.values().stream().map(pair -> pair.first).collect(Collectors.toList());
                        for (String word: words) {
                            popupMenu.getMenu().add(word);
                        }
                        popupMenu.setOnMenuItemClickListener((menuItem) -> {
                            ((TextView) view).setText(menuItem.getTitle());
                            return true;
                        });
                        popupMenu.show();
                    });
                }

                cell.setGravity(Gravity.CENTER);

                tableRow.addView(cell);
            }
            sudokuGrid.addView(tableRow);
        }
    }
}