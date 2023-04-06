package com.foxtrot.sudoku.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;
import com.foxtrot.sudoku.R;
import com.foxtrot.sudoku.model.AppConstants;
import com.foxtrot.sudoku.model.BoardSize;

public class MainMenuActivity extends AppCompatActivity {

    public static final String BOARD_SIZE_TAG = "board_size";

    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        displayMainMenuButtons();
        checkBox = findViewById(R.id.custom_words_checkbox);
        checkBox.setChecked(AppConstants.useCustomWords);

        // Call the setUseCustomWords method whenever the checkbox state changes
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                AppConstants.setUseCustomWords(isChecked);
            }
        });
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.custom_words_checkbox:
                AppConstants.setUseCustomWords(checked);
                break;
        }
    }

    private void displayMainMenuButtons() {
        Button next4x4Button = (Button) findViewById(R.id.button_four);
        next4x4Button.setOnClickListener(view -> {
            Intent intent = new Intent(MainMenuActivity.this, SudokuActivity.class);
            intent.putExtra(BOARD_SIZE_TAG, BoardSize._4X4.name());
            startActivity(intent);
        });

        Button next6x6Button = (Button) findViewById(R.id.button_six);
        next6x6Button.setOnClickListener(view -> {
            Intent intent = new Intent(MainMenuActivity.this, SudokuActivity.class);
            intent.putExtra(BOARD_SIZE_TAG, BoardSize._6X6.name());
            startActivity(intent);
        });

        Button next9x9Button = (Button) findViewById(R.id.button_nine);
        next9x9Button.setOnClickListener(view -> {
            Intent intent = new Intent(MainMenuActivity.this, SudokuActivity.class);
            intent.putExtra(BOARD_SIZE_TAG, BoardSize._9X9.name());
            startActivity(intent);
        });

        Button next12x12Button = (Button) findViewById(R.id.button_twelve);
        next12x12Button.setOnClickListener(view -> {
            Intent intent = new Intent(MainMenuActivity.this, SudokuActivity.class);
            intent.putExtra(BOARD_SIZE_TAG, BoardSize._12X12.name());
            startActivity(intent);
        });

        Button customButton = (Button) findViewById(R.id.button_custom);
        customButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainMenuActivity.this, CustomWordsActivity.class);
            startActivity(intent);
        });
    }

}
