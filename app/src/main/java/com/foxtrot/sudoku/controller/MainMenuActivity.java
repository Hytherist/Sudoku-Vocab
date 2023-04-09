package com.foxtrot.sudoku.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import androidx.appcompat.app.AppCompatActivity;
import com.foxtrot.sudoku.R;
import com.foxtrot.sudoku.model.BoardLanguage;
import com.foxtrot.sudoku.model.BoardSize;
import com.foxtrot.sudoku.model.GameMode;

public class MainMenuActivity extends AppCompatActivity {

    public static final String BOARD_SIZE_TAG = "board_size";

    public static final String GAME_MODE = "game_mode";

    public static final String BOARD_LANGUAGE = "board_language";

    private GameMode selectedGameMode;

    private BoardLanguage selectedBoardLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);



        selectedGameMode = GameMode.NORMAL;
        selectedBoardLanguage = BoardLanguage.FRENCH;


        displayMainMenuButtons();
    }

    private void displayMainMenuButtons() {

        Button instructionBtn = findViewById(R.id.button_instruction);
        instructionBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, InstructionActivity.class);
            startActivity(intent);
        });

        Button next4x4Button = findViewById(R.id.button_four);
        next4x4Button.setOnClickListener(view -> {
            Intent intent = new Intent(this, SudokuActivity.class);
            intent.putExtra(BOARD_SIZE_TAG, BoardSize._4X4.name());
            intent.putExtra(GAME_MODE, selectedGameMode.name());
            intent.putExtra(BOARD_LANGUAGE, selectedBoardLanguage.name());
            startActivity(intent);
        });

        Button next6x6Button = findViewById(R.id.button_six);
        next6x6Button.setOnClickListener(view -> {
            Intent intent = new Intent(this, SudokuActivity.class);
            intent.putExtra(BOARD_SIZE_TAG, BoardSize._6X6.name());
            intent.putExtra(GAME_MODE, selectedGameMode.name());
            intent.putExtra(BOARD_LANGUAGE, selectedBoardLanguage.name());
            startActivity(intent);
        });

        Button next9x9Button = findViewById(R.id.button_nine);
        next9x9Button.setOnClickListener(view -> {
            Intent intent = new Intent(this, SudokuActivity.class);
            intent.putExtra(BOARD_SIZE_TAG, BoardSize._9X9.name());
            intent.putExtra(GAME_MODE, selectedGameMode.name());
            intent.putExtra(BOARD_LANGUAGE, selectedBoardLanguage.name());
            startActivity(intent);
        });

        Button next12x12Button = findViewById(R.id.button_twelve);
        next12x12Button.setOnClickListener(view -> {
            Intent intent = new Intent(this, SudokuActivity.class);
            intent.putExtra(BOARD_SIZE_TAG, BoardSize._12X12.name());
            intent.putExtra(GAME_MODE, selectedGameMode.name());
            intent.putExtra(BOARD_LANGUAGE, selectedBoardLanguage.name());
            startActivity(intent);
        });


        RadioButton frenchButton = findViewById(R.id.board_language_french);
        frenchButton.setChecked(true);
        frenchButton.setOnClickListener(this::onBoardLanguageSelected);

        RadioButton englishButton = findViewById(R.id.board_language_english);
        englishButton.setOnClickListener(this::onBoardLanguageSelected);

        RadioButton normalButton = findViewById(R.id.game_mode_normal);
        normalButton.setChecked(true);
        normalButton.setOnClickListener(view -> {
            onGameModeSelected(view);
            frenchButton.setEnabled(true);
            englishButton.setEnabled(true);
        });

        RadioButton listeningComprehensionButton = findViewById(R.id.game_mode_listening_comprehension);
        listeningComprehensionButton.setOnClickListener(view -> {
            onGameModeSelected(view);
            frenchButton.setEnabled(false);
            englishButton.setEnabled(false);

            frenchButton.setChecked(true);
            selectedBoardLanguage = BoardLanguage.FRENCH;
        });
    }

    public void onBoardLanguageSelected(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.board_language_english:
                if (checked) {
                    selectedBoardLanguage = BoardLanguage.ENGLISH;
                }
                break;
            case R.id.board_language_french:
                if (checked) {
                    selectedBoardLanguage = BoardLanguage.FRENCH;
                }
                break;
        }
    }

    public void onGameModeSelected(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.game_mode_normal:
                if (checked) {
                    selectedGameMode = GameMode.NORMAL;
                }
                break;
            case R.id.game_mode_listening_comprehension:
                if (checked) {
                    selectedGameMode = GameMode.LISTENING_COMPREHENSION;
                }
                break;
        }
    }
}
