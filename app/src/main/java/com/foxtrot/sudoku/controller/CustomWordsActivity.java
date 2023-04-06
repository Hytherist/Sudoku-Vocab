package com.foxtrot.sudoku.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.foxtrot.sudoku.R;
import com.foxtrot.sudoku.model.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomWordsActivity extends Activity {
    private LinearLayout wordsContainer;
    private Button addButton;
    private Button submitButton;

    private boolean hasCustomWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_word);

        wordsContainer = findViewById(R.id.wordsContainer);
        addButton = findViewById(R.id.addButton);
        submitButton = findViewById(R.id.submitButton);

        File file = new File(getFilesDir(), "custom_words.txt");
        if (file.exists() && file.length() > 0) {
            hasCustomWords = true;

            List<Pair<String, String>> wordPairs = new ArrayList<>();
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(":");
                    if (parts.length == 2) {
                        Pair<String, String> pair = new Pair<>(parts[0], parts[1]);
                        wordPairs.add(pair);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Custom words found");
            builder.setMessage("Do you want to clear the existing custom words or add more?\n\n" + wordPairsToString(wordPairs));
            builder.setPositiveButton("Clear", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    try {
                        FileWriter writer = new FileWriter(file, false);
                        writer.write("");
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            builder.setNegativeButton("Add more", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            hasCustomWords = false;
        }


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addWordPair();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitWords();
            }
        });
    }

    private String wordPairsToString(List<Pair<String, String>> wordPairs) {
        if (wordPairs.isEmpty()) {
            return "No custom words found.";
        } else {
            StringBuilder sb = new StringBuilder();
            for (Pair<String, String> pair : wordPairs) {
                sb.append(pair.first).append(" - ").append(pair.second).append("\n");
            }
            return sb.toString();
        }
    }

    private void addWordPair() {
        EditText word1EditText = findViewById(R.id.word1EditText);
        EditText word2EditText = findViewById(R.id.word2EditText);
        String word1 = word1EditText.getText().toString();
        String word2 = word2EditText.getText().toString();

        StringBuilder wordPairsBuilder = new StringBuilder();
        wordPairsBuilder.append(word1).append(",").append(word2).append("\n");

        TextView wordPairTextView = new TextView(this);
        wordPairTextView.setText(wordPairsBuilder.toString());
        wordsContainer.addView(wordPairTextView);

        if (wordsContainer.getChildCount() >= 12) {
            Toast.makeText(this, "Maximum number of word pairs reached", Toast.LENGTH_SHORT).show();
            addButton.setEnabled(false);
        }

        File file = new File(getFilesDir(), "custom_words.txt");
        try {
            FileWriter writer = new FileWriter(file, true); // append to the file
            writer.write(wordPairsBuilder.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void submitWords() {
        List<Pair<String, String>> wordPairs = new ArrayList<>();
        File file = new File(getFilesDir(), "custom_words.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(",");
                if (words.length == 2) {
                    Pair<String, String> pair = new Pair<>(words[0], words[1]);
                    wordPairs.add(pair);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder messageBuilder = new StringBuilder();
        for (Pair<String, String> pair : wordPairs) {
            messageBuilder.append(pair.first).append(" - ").append(pair.second).append("\n");
        }
        String message = messageBuilder.toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton("OK", (dialog, id) -> {
            dialog.dismiss();
            Intent intent = new Intent(CustomWordsActivity.this, MainMenuActivity.class);
            startActivity(intent);
            finish();
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
