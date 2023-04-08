package com.foxtrot.sudoku.controller;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.speech.tts.TextToSpeech;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import com.foxtrot.sudoku.R;
import com.foxtrot.sudoku.model.BoardLanguage;
import com.foxtrot.sudoku.model.BoardSize;
import com.foxtrot.sudoku.model.Game;
import com.foxtrot.sudoku.model.GameMode;
import com.foxtrot.sudoku.model.Pair;
import com.foxtrot.sudoku.view.SudokuCellView;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class SudokuActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private Game game;

    private BoardSize boardSize;

    private GameMode gameMode;

    private BoardLanguage boardLanguage;

    private int hintCounter = 0;

    private long startTime = 0;

    private boolean eraseButtonClicked = false;

    private long stopwatchEndTime;

    private Handler stopwatchHandler = new Handler();

    private TextView stopwatchTextView;

    private Runnable stopwatchRunnable = new Runnable() {
        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            String time = String.format(Locale.CANADA, "%02d:%02d", minutes, seconds);

            if (stopwatchTextView != null) {
                stopwatchTextView.setText(time);
            }

            stopwatchHandler.postDelayed(this, 500);
        }
    };

    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String boardSizeString = intent.getStringExtra(MainMenuActivity.BOARD_SIZE_TAG);
        boardSize = BoardSize.valueOf(boardSizeString);

        String gameModeString = intent.getStringExtra(MainMenuActivity.GAME_MODE);
        gameMode = GameMode.valueOf(gameModeString);

        String boardLanguageString = intent.getStringExtra(MainMenuActivity.BOARD_LANGUAGE);
        boardLanguage = BoardLanguage.valueOf(boardLanguageString);

        // Create the model
        game = new Game();
        game.start(boardSize);

        // Create the view
        setContentView(R.layout.activity_sudoku);
        displayBoard();
        addSubmitButton();
        addBackButton();
        addHintButton();
        addRestartButton();
        addEraseButton();

        stopwatchTextView = findViewById(R.id.stopwatch_text_view);
        startStopwatch();

        textToSpeech = new TextToSpeech(this, this);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_sudoku);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_sudoku);
        }

        displayBoard();
        addSubmitButton();
        addBackButton();
        addHintButton();
        addRestartButton();
        addEraseButton();

        stopwatchTextView = findViewById(R.id.stopwatch_text_view);

        textToSpeech = new TextToSpeech(this, this);
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            // Set the language
            int result = textToSpeech.setLanguage(Locale.CANADA_FRENCH);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // Language not supported
                Log.e("TextToSpeech", "Language not supported");
            }
        } else {
            // Initialization failed
            Log.e("TextToSpeech", "Initialization failed");
        }
    }

    private void addBackButton() {
        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(view -> {
            reset();
            startActivity(new Intent(SudokuActivity.this, MainMenuActivity.class));
        });
    }

    private void displayBoard() {
        GridLayout sudokuBoard = findViewById(R.id.sudoku_table);
        sudokuBoard.removeAllViews();

        int size = boardSize.getSize();
        sudokuBoard.setRowCount(size);
        sudokuBoard.setColumnCount(size);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                SudokuCellView cell;
                if (gameMode == GameMode.LISTENING_COMPREHENSION) {
                    cell = initializeCellForListeningComprehension(i, j);
                } else {
                    cell = initializeCell(i, j);
                }
                sudokuBoard.addView(cell);
            }
        }
    }

    private SudokuCellView initializeCell(int row, int col) {
        int value = game.getBoard().getValue(row, col);
        Pair<String, String> wordPair = game.getWordMap().get(value);
        boolean clickable = game.getQuestion().getValue(row, col) == 0;
        SudokuCellView cell = new SudokuCellView(this, boardSize, row, col, clickable, wordPair, gameMode, value, boardLanguage);
        if (clickable) {
            cell.setOnClickListener(view -> onCellClick(view, row, col));
        }
        return cell;
    }

    private SudokuCellView initializeCellForListeningComprehension(int row, int col) {
        int value = game.getSolution().getValue(row, col);
        Pair<String, String> wordPair = game.getWordMap().get(value);
        SudokuCellView cell = new SudokuCellView(this, boardSize, row, col, true, wordPair, gameMode, value, boardLanguage);
        cell.setOnClickListener(view -> {
            // Speak text
            String text = wordPair.getSecond();
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);

            Map<Integer, Pair<String, String>> wordMap = game.getWordMap();
            PopupMenu popupMenu = new PopupMenu(this, view);

            for (Map.Entry<Integer, Pair<String, String>> mapping : wordMap.entrySet()) {
                popupMenu.getMenu().add(1, mapping.getKey(), Menu.NONE, mapping.getValue().first);
            }

            popupMenu.setOnMenuItemClickListener(menuItem -> {
                if (menuItem.getItemId() == value) {
                    view.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light, null));
                } else {
                    view.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light, null));
                }
                return true;
            });

            popupMenu.show();
        });
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

        Map<Integer, Pair<String, String>> wordMap = game.getWordMap();
        PopupMenu popupMenu = new PopupMenu(this, view);
        view.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.cell_current, null));

        for (Map.Entry<Integer, Pair<String, String>> mapping : wordMap.entrySet()) {
            popupMenu
                .getMenu()
                .add(1, mapping.getKey(), Menu.NONE, boardLanguage == BoardLanguage.ENGLISH ? mapping.getValue().second : mapping.getValue().first);
        }

        popupMenu.setOnMenuItemClickListener(menuItem -> {
            Pair<String, String> wordPair = Objects.requireNonNull(wordMap.get(menuItem.getItemId()));
            ((TextView) view).setText(boardLanguage == BoardLanguage.ENGLISH ? wordPair.first : wordPair.second);
            game.getBoard().setValue(row, col, menuItem.getItemId());
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

            if (game.validate()) {
                stopStopwatch();
                String timeText = stopwatchTextView.getText().toString();
                String message = "Correct! You have used <b>\"" + hintCounter + "\"</b> hint(s)." + "<br>Your time was: " + timeText;
                builder.setMessage(Html.fromHtml(message, Html.FROM_HTML_MODE_LEGACY));
            } else {
                builder.setMessage("Incorrect!");
            }

            builder.setNeutralButton("Close", (dialog, id) -> dialog.dismiss());

            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }

    private void addHintButton() { // hint button
        Button button = findViewById(R.id.hint_button);
        button.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Hint");

            // Check if a cell is clicked
            Optional<Pair<Integer, Integer>> hintPositionOptional = game.getHintPosition();
            if (hintPositionOptional.isPresent()) {
                hintCounter++;
                int row = hintPositionOptional.get().getFirst();
                int col = hintPositionOptional.get().getSecond();
                String hint = Objects.requireNonNull(game.getWordMap().get(game.getSolution().getValue(row, col))).getFirst();
                String message = String.format(
                    Locale.CANADA,
                    "The hint is: <b>%s</b> at position (%d, %d)\nHint used: %d",
                    hint,
                    row,
                    col,
                    hintCounter
                );
                builder.setMessage(Html.fromHtml(message, Html.FROM_HTML_MODE_LEGACY));
            } else {
                builder.setMessage("Your solution is already correct!");
            }

            builder.setNeutralButton("Close", (dialog, id) -> dialog.dismiss());

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

            builder.setNegativeButton("No", (dialog, id) -> dialog.dismiss());

            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }

    private void reset() {
        game.reset();
        displayBoard();
        hintCounter = 0;
    }

    private void addEraseButton() {
        Button button = findViewById(R.id.erase_button);

        button.setOnClickListener(view -> {
            eraseButtonClicked = true;
            button.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        });
    }

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
            stopwatchTextView.setText(R.string.stopwatch_time_initial);
        }
    }
}
