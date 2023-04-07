package com.foxtrot.sudoku.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.foxtrot.sudoku.R;
import com.foxtrot.sudoku.model.BoardSize;
import com.foxtrot.sudoku.model.Game;

public class MainMenuActivity extends AppCompatActivity {

    public static final String BOARD_SIZE_TAG = "board_size";
    public static final String Board_language_option = "frenchToenglish";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        FRlangBtnClick();

        ENlangBtnClick();

        displayMainMenuButtons();

    }
    public void FRlangBtnClick() {
        Button frlangBtn = (Button)findViewById(R.id.fr_en);
        frlangBtn.setOnClickListener(view -> {
//            Intent intent = new Intent(MainMenuActivity.this, Game.class);
////            intent.putExtra("Board_language_option", "frenchToenglish");
////            startActivity(intent);
//
//            Bundle extras = new Bundle();
//            extras.putString("key", "value1");
//            intent.putExtras(extras);
//            startActivity(intent);
            String frdata = "frToen";
            Game nonActivityJavaClass = new Game();
            nonActivityJavaClass.setData(frdata);
        });



    }
    public void ENlangBtnClick() {
        Button enlangBtn = (Button)findViewById(R.id.en_fr);
        enlangBtn.setOnClickListener(view -> {
//            Intent intent = new Intent(MainMenuActivity.this, Game.class);
////            intent.putExtra("Board_language_option", "englishTofrench");
////            startActivity(intent);
//            Bundle extras = new Bundle();
//            extras.putString("key", "value1");
//            intent.putExtras(extras);
//            startActivity(intent);
            String endata = "enTofr";
            Game nonActivityJavaClass = new Game();
            nonActivityJavaClass.setData(endata);
        });

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
    }
}
