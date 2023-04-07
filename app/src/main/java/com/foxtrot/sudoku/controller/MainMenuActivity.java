package com.foxtrot.sudoku.controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.foxtrot.sudoku.R;
import com.foxtrot.sudoku.model.BoardSize;
import com.foxtrot.sudoku.model.GameMode;

public class MainMenuActivity extends AppCompatActivity {

    public static final String BOARD_SIZE_TAG = "board_size";

    public static final String GAME_MODE = "game_mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        displayMainMenuButtons();
    }

    private void displayMainMenuButtons() {
        Button next4x4Button = findViewById(R.id.button_four);
        next4x4Button.setOnClickListener(view -> {
            Intent intent = new Intent(this, SudokuActivity.class);
            intent.putExtra(BOARD_SIZE_TAG, BoardSize._4X4.name());
            intent.putExtra(GAME_MODE, GameMode.NORMAL.name());
            startActivity(intent);
        });

        Button next6x6Button = findViewById(R.id.button_six);
        next6x6Button.setOnClickListener(view -> {
            Intent intent = new Intent(this, SudokuActivity.class);
            intent.putExtra(BOARD_SIZE_TAG, BoardSize._6X6.name());
            intent.putExtra(GAME_MODE, GameMode.NORMAL.name());
            startActivity(intent);
        });

        Button next9x9Button = findViewById(R.id.button_nine);
        next9x9Button.setOnClickListener(view -> {
            Intent intent = new Intent(this, SudokuActivity.class);
            intent.putExtra(BOARD_SIZE_TAG, BoardSize._9X9.name());
            intent.putExtra(GAME_MODE, GameMode.NORMAL.name());
            startActivity(intent);
        });

        Button next12x12Button = findViewById(R.id.button_twelve);
        next12x12Button.setOnClickListener(view -> {
            Intent intent = new Intent(this, SudokuActivity.class);
            intent.putExtra(BOARD_SIZE_TAG, BoardSize._12X12.name());
            intent.putExtra(GAME_MODE, GameMode.NORMAL.name());
            startActivity(intent);
        });

        Button listeningComprehensionButton = findViewById(R.id.button_listening_comprehension);
        listeningComprehensionButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, SudokuActivity.class);
            intent.putExtra(BOARD_SIZE_TAG, BoardSize._4X4.name());
            intent.putExtra(GAME_MODE, GameMode.LISTENING_COMPREHENSION.name());
            startActivity(intent);
        });
    }
}
