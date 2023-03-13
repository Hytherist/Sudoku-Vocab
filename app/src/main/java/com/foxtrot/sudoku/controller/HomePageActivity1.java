package com.foxtrot.sudoku.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.foxtrot.sudoku.R;
import com.foxtrot.sudoku.models.BoardSize;

public class HomePageActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page1);
        configureNext4X4Button();
    }

    private void configureNext4X4Button(){
        Button next4x4Button = (Button) findViewById(R.id.button_four);
        next4x4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //startActivity(new Intent(HomePageActivity1.this, MainActivity.class));
                Intent intent = new Intent(HomePageActivity1.this, MainActivity.class);
                intent.putExtra("new_boardSize_value4",BoardSize._4X4);
                startActivity(intent);
            }
        });
        Button next9x9Button = (Button) findViewById(R.id.button_nine);
        next9x9Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(HomePageActivity1.this, MainActivity.class));
                Intent intent = new Intent(HomePageActivity1.this, MainActivity.class);
                intent.putExtra("new_boardSize_value9",BoardSize._9X9 );
                startActivity(intent);
            }
        });
        Button next6x6Button = (Button) findViewById(R.id.button_six);
        next6x6Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(HomePageActivity1.this, MainActivity.class));
                Intent intent = new Intent(HomePageActivity1.this, MainActivity.class);
                intent.putExtra("new_boardSize_value6",BoardSize._6X6 );
                startActivity(intent);
            }
        });

        Button next12x12Button = (Button) findViewById(R.id.button_twelve);
        next12x12Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(HomePageActivity1.this, MainActivity.class));
                Intent intent = new Intent(HomePageActivity1.this, MainActivity.class);
                intent.putExtra("new_boardSize_value12",BoardSize._12X12 );
                startActivity(intent);
            }
        });
    }
}