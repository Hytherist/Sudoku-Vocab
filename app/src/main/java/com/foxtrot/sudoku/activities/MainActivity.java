package com.foxtrot.sudoku.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import com.foxtrot.sudoku.R;
import com.foxtrot.sudoku.models.App;
import com.foxtrot.sudoku.models.Board;
import com.foxtrot.sudoku.models.BoardSize;
import com.foxtrot.sudoku.models.Pair;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private App app;

    // TODO: ask for user input
    public static BoardSize boardSize = BoardSize._4X4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("new_boardSize_value9")) {
            boardSize = BoardSize._9X9;
        }
        if (intent != null && intent.hasExtra("new_boardSize_value12")) {
            boardSize = BoardSize._12X12;
        }
        if (intent != null && intent.hasExtra("new_boardSize_value4")) {
            boardSize = BoardSize._4X4;
        }
        if (intent != null && intent.hasExtra("new_boardSize_value6")) {
            boardSize = BoardSize._6X6;
        }
        // Create the model
        app = new App();
        app.start(boardSize);

        // Create the view
        setContentView(R.layout.activity_main);
        displayBoard();
        addSubmitButton();
        configureBackButton();
    }

    private void configureBackButton(){
        Button backButton = (Button) findViewById(R.id.backHome_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, HomePageActivity1.class));
            }
        });
    }

    private void displayBoard() {
        TableLayout sudokuBoard = findViewById(R.id.sudoku_table);
        sudokuBoard.setStretchAllColumns(true);

        for (int i = 0; i < boardSize.getSize(); i++) {
            TableRow tableRow = new TableRow(this);
            for (int j = 0; j < boardSize.getSize(); j++) {
                TextView cell = initializeCell(i, j);
                tableRow.addView(cell);
            }
            sudokuBoard.addView(tableRow);
        }
    }

    private TextView initializeCell(int row, int col) {
        Board board = app.getBoard();
        Map<Integer, Pair<String, String>> wordMap = app.getWordMap();

        TextView cell = new TextView(this);
        cell.setGravity(Gravity.CENTER);

        // Set initial word
        int value = board.getValue(row, col);
        Pair<String, String> wordPair = wordMap.get(value);
        boolean empty = wordPair == null;
        if (empty) {
            Typeface normalTypeface = Typeface.defaultFromStyle(Typeface.NORMAL);
            cell.setTypeface(normalTypeface);
            cell.setTextColor(Color.BLUE);
            cell.setText("");
            cell.setOnClickListener(view -> {
                PopupMenu popupMenu = new PopupMenu(this, view);
                for (Map.Entry<Integer, Pair<String, String>> mapping : wordMap.entrySet()) {
                    popupMenu.getMenu().add(1, mapping.getKey(), Menu.NONE, mapping.getValue().first);
                }

                popupMenu.setOnMenuItemClickListener(menuItem -> {
                    ((TextView) view).setText(wordMap.get(menuItem.getItemId()).second);
                    board.setValue(row, col, menuItem.getItemId());
                    return true;
                });

                popupMenu.show();
            });
        } else {
            Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
            cell.setTypeface(boldTypeface);
            cell.setText(wordPair.second);
        }

        // Set background
        int toggle = (row / boardSize.getGridRowSize() + col / boardSize.getGridColSize()) % 2;
        cell.setBackground(ResourcesCompat.getDrawable(getResources(), toggle == 0 ? R.drawable.cellteal : R.drawable.cellwhite, null));

        return cell;
    }

    private void addSubmitButton() {
        Button button = findViewById(R.id.submit_button);
        button.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Solution");

            if (app.validate()) {
                builder.setMessage("Correct!");
            } else {
                builder.setMessage("Incorrect!");
            }

            builder.setPositiveButton(
                "Close",
                (dialog, id) -> {
                    dialog.dismiss();
                }
            );

            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }
}
