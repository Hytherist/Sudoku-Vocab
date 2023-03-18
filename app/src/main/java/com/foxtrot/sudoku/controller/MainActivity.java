package com.foxtrot.sudoku.controller;


import android.content.res.ColorStateList;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.Html;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.foxtrot.sudoku.R;

import com.foxtrot.sudoku.model.App;
import com.foxtrot.sudoku.model.Board;
import com.foxtrot.sudoku.model.BoardSize;
import com.foxtrot.sudoku.model.Pair;
import com.foxtrot.sudoku.view.SudokuCellView;

import java.util.Map;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private App app;

    // TODO: ask for user input
    private BoardSize boardSize = BoardSize._4X4;

    private int hintCounter = 0;

    private long startTime = 0;

    private boolean eraseButtonClicked = false;

    private long stopwatchEndTime;
    private Handler stopwatchHandler = new Handler();
    private TextView stopwatchTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("new_boardSize_value12")) {
            boardSize = BoardSize._12X12;
        }
        if (intent != null && intent.hasExtra("new_boardSize_value9")) {
            boardSize = BoardSize._9X9;
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

        addHintButton();

        addRestartButton();

        addEraseButton();

        startStopwatch();

        stopwatchTextView = findViewById(R.id.stopwatchTextView);
    }

    private void displayBoard() {
        GridLayout sudokuBoard = findViewById(R.id.sudoku_table);
        int size = boardSize.getSize();
        sudokuBoard.setRowCount(size);
        sudokuBoard.setColumnCount(size);

        sudokuBoard.removeAllViews();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                SudokuCellView cell = initializeCell(i, j);
                sudokuBoard.addView(cell);
            }
        }
    }

    private SudokuCellView initializeCell(int row, int col) {
        Board board = app.getBoard();
        Map<Integer, Pair<String, String>> wordMap = app.getWordMap();

        SudokuCellView cell = new SudokuCellView(this, boardSize.getSize());
        cell.setGravity(Gravity.CENTER);

        int value = board.getValue(row, col);
        Pair<String, String> wordPair = wordMap.get(value);
        boolean emptyCell = wordPair == null;
        if (emptyCell) {
            Typeface normalTypeface = Typeface.defaultFromStyle(Typeface.NORMAL);
            cell.setTypeface(normalTypeface);
            cell.setTextColor(Color.BLUE);
            cell.setText("");
            cell.setOnClickListener(view -> onCellClick(view, row, col));
        } else {
            Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
            cell.setTypeface(boldTypeface);
            cell.setTextColor(Color.BLACK);
            cell.setText(wordPair.second);
        }

        // Set background
        int toggle = (row / boardSize.getGridRowSize() + col / boardSize.getGridColSize()) % 2;
        cell.setBackground(ResourcesCompat.getDrawable(getResources(), toggle == 0 ? R.drawable.cellbeige : R.drawable.cellwhite, null));

        return cell;
    }

    private void onCellClick(View view, int row, int col) {

        Drawable originalBackground = view.getBackground();
        Button button = findViewById(R.id.erase_button);

        if (eraseButtonClicked) {
            ((TextView) view).setText("");
            eraseButtonClicked = false;
            view.setBackground(originalBackground);
            button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00FF00")));
            return;
        }

        Map<Integer, Pair<String, String>> wordMap = app.getWordMap();
        PopupMenu popupMenu = new PopupMenu(this, view);
        view.setBackground(getResources().getDrawable(R.drawable.cellcurrent, null));

        for (Map.Entry<Integer, Pair<String, String>> mapping : wordMap.entrySet()) {
            popupMenu.getMenu().add(1, mapping.getKey(), Menu.NONE, mapping.getValue().first);
        }

        popupMenu.setOnMenuItemClickListener(menuItem -> {
            ((TextView) view).setText(Objects.requireNonNull(wordMap.get(menuItem.getItemId())).second);
            app.getBoard().setValue(row, col, menuItem.getItemId());
            return true;
        });

        popupMenu.setOnDismissListener(menu -> {
            view.setBackground(originalBackground);
        });

        popupMenu.show();

        eraseButtonClicked = false;
    }

    private void addSubmitButton() {
        Button button = findViewById(R.id.submit_button);
        button.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Solution");

            if (app.validate()) {
                stopStopwatch();
                String timeText = stopwatchTextView.getText().toString();
                String message = "Correct! You have used <b>\"" + hintCounter + "\"</b> hint(s)." + "<br>Your time was: " + timeText;
                builder.setMessage(Html.fromHtml(message));
            } else {
                builder.setMessage("Incorrect!");
            }

            builder.setPositiveButton("Close", (dialog, id) -> dialog.dismiss());

            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }

    private void addHintButton() { // hint button
        Button button = findViewById(R.id.hint_button);
        button.setOnClickListener(view -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Hint");

            Map<Integer, Pair<String, String>> wordMap = app.getWordMap();

            Integer hintPosition = app.getHintPosition();


            if (hintPosition != null) { // checks if board solved
                int insideGrid = (int) Math.sqrt(app.getBoard().getSize()); // gets the 2x2, 3x3, 4x4
                int insideRow = hintPosition / insideGrid; // row
                int insideCol = hintPosition % insideGrid; // col
                int cellRow = -1;
                int cellCol = -1;
                int[][] currentBoard = app.getBoard().getValues(); // gets the numbers from the array
                int[][] solution = app.getSolution().getValues();
                String hint = "";

                for (int i = insideRow * insideGrid; i < insideRow * insideGrid + insideGrid; i++) {
                    for (int j = insideCol * insideGrid; j < insideCol * insideGrid + insideGrid; j++) {
                        if (currentBoard[i][j] != solution[i][j]) { // check if currentBoard has a cell thats incorrect/not filled
                            hint = wordMap.get(solution[i][j]).getSecond(); // sets the string as the hint
                            cellRow = i;
                            cellCol = j;
                            break;
                        }
                    }
                }

                hintCounter++;
                String message = "The hint is: <b>" + hint + "</b> at position (" + cellCol + ", " + cellRow + ")<br>"
                        + "You have used \"<b>" + hintCounter + "\"</b> hint(s).";

                builder.setMessage(Html.fromHtml(message));
            } else {
                builder.setMessage("Your Solution is correct!");
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

    private void addRestartButton() {
        Button button = findViewById(R.id.restart_button);
        button.setOnClickListener(view -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Would you like to restart the game?");

            builder.setPositiveButton(
                    "Yes",
                    (dialog, id) -> {
                        reset();
                        resetStopwatch();
                        startStopwatch();
                    }
            );

            builder.setNegativeButton(
                    "No",
                    (dialog, id) -> {
                        dialog.dismiss();
                    }
            );

            AlertDialog dialog = builder.create();
            dialog.show();

        });
    }

    private void reset() {
        Board board = app.getBoard();
        board.reset();
        displayBoard();
        hintCounter = 0;
    }

    private void addEraseButton() {
        Button button = findViewById(R.id.erase_button);
        button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00FF00")));

        button.setOnClickListener(view -> {
            eraseButtonClicked = true;
            button.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        });
    }

    private Runnable stopwatchRunnable = new Runnable() {
        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            String time = String.format("%02d:%02d", minutes, seconds);

            if (stopwatchTextView != null) {
                stopwatchTextView.setText(time);
            }

            stopwatchHandler.postDelayed(this, 500);
        }
    };

    private void startStopwatch() {
        startTime = System.currentTimeMillis();
        stopwatchHandler.postDelayed(stopwatchRunnable, 0);
    }

    private void stopStopwatch() {
        stopwatchEndTime = SystemClock.elapsedRealtime();
        stopwatchHandler.removeCallbacks(stopwatchRunnable);
    }

    private void resetStopwatch() {
        stopStopwatch();
        if (stopwatchTextView != null) {
            stopwatchTextView.setText("00:00");
        }
    }

}
