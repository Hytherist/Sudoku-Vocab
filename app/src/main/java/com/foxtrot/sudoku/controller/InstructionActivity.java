package com.foxtrot.sudoku.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.foxtrot.sudoku.R;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.foxtrot.sudoku.model.BoardSize;
import com.foxtrot.sudoku.model.Game;


public class InstructionActivity extends AppCompatActivity {
    final String aboutGameText = "Welcome to Language Sudoku!"+"\n" +
    "We are excited that you want to learn a new language while playing sudoku." +"\n" +
   "A sudoku board has various available dimensions such as 4x4, 6x6, 9x9 and 12x12. Each of the board sizes corresponds to the level of difficulty of the game where 12x12 is the hardest level, 9x9 is the medium level and 4x4, 6x6 are easy levels." +"\n"+
    "What's different about this game? Our game allows you to learn a new language by practicing your vocabulary skills. Instead of a traditional sudoku board that consists of numbers, this game shows words on the board and the player must fill the missing words of the board by choosing words in a different language from a dropdown to test their language learning skills." +"\n";
    final String gamesetupText = "Here are the rules of the game:\n" +
            "1: The player must first select a Game Mode between Normal mode and Listening comprehension mode.\n" +
            "   -  Normal mode shows words(e.g French words) on the board and the player selects words from a dropdown menu of words in another language(E.g English words) to completely fill the board by following the general rules of a sudoku game which allows no repetition of words horizontally nor vertically. Once the board has been filled fully, the player can submit the board to verify their answer. \n" +
            "   - Listening Comprehension mode numbers appear in the prefilled cells and when the player presses a number, the corresponding word in the language that they are learning will be read out to them. In order to test their listening comprehension, the player  selects from the dropdown the correct English translation of the word. Once the board has been filled fully, the player can submit the board to verify their answer. \n"
            +"\n" +
            "2: Which language appears on the board and which appears in the dropdown must be chosen next. \n" +
            "   - French to English option displays French on the sudoku board and english in the dropdown\n" +
            "   - English to French option displays English on the board and the French in the dropdown\n"
            +"\n" +
            "3: Game dimensions corresponding to the level of difficulties can be chosen from.\n" +
            "   - 4x4 is Easy level with 4 rows and 4 columns \n" +
            "   - 6x6 is Easy level with 6 rows and 6 columns\n" +
            "   - 9x9 is Medium level with 9 rows and 9 columns\n" +
            "   - 12x12 is Hard level with 12 rows and 12 columns\n"
            +"\n" +
            "4: All done! The sudoku board should now be generated\n";
    final String howToplayText = "The gameplay is very similar to a traditional Sudoku game of just numbers. The goal of the game is to fill the entire board with words such that no one word appears horizontally and vertically more than once. It must be emphasised that the word in either French or English which has the same meaning cannot appear more than once horizontally and vertically.\n";
    final String gamefeaturesText = "Hint Button: If you get stuck in the game and are not sure what your next move should be. Then the hint button would provide you with the exact value you can enter into a cell so allow you to continue playing the game.\n" +
            "\n" +
            "Erase button: Clicked the wrong option or want to change the value of a cell to a different value? Then by clicking on the erase button and then selecting a cell on the board, you are able to clear that cell of its previous value\n" +
            "\n" +
            "Reset Button: Clicking on the reset button deletes all the changes made previously and generates a fresh new board with pre-filled cells and some empty cells.\n" +
            "Home Button: Clicking on the Home button takes you back to the home page where you can select from a variety of options and start a new game\n" +
            "Submit Button: Once the board has been filled entirely to check if the board has been completed correctly or incorrectly, click on the submit and it will let you know the results of your game. Note that this button cannot be pressed until the full board has been filled.\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

        Button home_backBtn = findViewById(R.id.buttonActivity1);
        home_backBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainMenuActivity.class);
            startActivity(intent);
        });
        createExperienceSection();
        creategameSetupSection();
        createHTPSection();
        createFeaturesSection();
        Toast.makeText(this, "Let's learn a new language with Language Sudoku!", Toast.LENGTH_LONG).show();
    }



    private void createFeaturesSection() {
        TextView education = findViewById(R.id.gameFeatures);
        education.setText(gamefeaturesText);

    }

    private void createHTPSection() {
        TextView experience = findViewById(R.id.howToPlay);
        experience.setText(howToplayText);

    }

    private void creategameSetupSection() {
        TextView experience = findViewById(R.id.gameSetup);
        experience.setText(gamesetupText);

    }

    private void createExperienceSection() {
        TextView experience = findViewById(R.id.aboutGame);
        experience.setText(aboutGameText);
    }



}
















