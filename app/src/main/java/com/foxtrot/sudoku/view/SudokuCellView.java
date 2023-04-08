package com.foxtrot.sudoku.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.res.ResourcesCompat;
import com.foxtrot.sudoku.R;
import com.foxtrot.sudoku.model.BoardLanguage;
import com.foxtrot.sudoku.model.BoardSize;
import com.foxtrot.sudoku.model.GameMode;
import com.foxtrot.sudoku.model.Pair;

public class SudokuCellView extends AppCompatTextView {

    private final BoardSize boardSize;

    private final int row;

    private final int col;

    private final boolean clickable;

    private final Pair<String, String> wordPair;

    private final int value;

    private final BoardLanguage boardLanguage;

    public SudokuCellView(
        @NonNull Context context,
        BoardSize boardSize,
        int row,
        int col,
        boolean clickable,
        Pair<String, String> wordPair,
        GameMode gameMode,
        int value,
        BoardLanguage boardLanguage
    ) {
        super(context);
        this.boardSize = boardSize;
        this.row = row;
        this.col = col;
        this.clickable = clickable;
        this.wordPair = wordPair;
        this.value = value;
        this.boardLanguage = boardLanguage;

        if (gameMode == GameMode.NORMAL) {
            initializeCell();
        } else {
            initializeCellForListeningComprehension();
        }
    }

    private void initializeCell() {
        setTextSize(TypedValue.COMPLEX_UNIT_SP, boardSize.getTextSize());
        if (clickable) {
            Typeface normalTypeface = Typeface.defaultFromStyle(Typeface.NORMAL);
            setTypeface(normalTypeface);
            setTextColor(Color.BLUE);
        } else {
            Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
            setTypeface(boldTypeface);
            setTextColor(Color.BLACK);
        }
        setText(wordPair == null ? "" : boardLanguage == BoardLanguage.ENGLISH ? wordPair.getFirst() : wordPair.getSecond());

        int toggle = (row / boardSize.getGridRowSize() + col / boardSize.getGridColSize()) % 2;
        setBackground(ResourcesCompat.getDrawable(getResources(), toggle == 0 ? R.drawable.cell_beige : R.drawable.cell_white, null));
        setGravity(Gravity.CENTER);
        setMaxLines(1);
    }

    private void initializeCellForListeningComprehension() {
        setTextSize(TypedValue.COMPLEX_UNIT_SP, boardSize.getTextSize());
        Typeface normalTypeface = Typeface.defaultFromStyle(Typeface.NORMAL);
        setTypeface(normalTypeface);
        setTextColor(Color.BLACK);
        setText(String.valueOf(value));

        int toggle = (row / boardSize.getGridRowSize() + col / boardSize.getGridColSize()) % 2;
        setBackground(ResourcesCompat.getDrawable(getResources(), toggle == 0 ? R.drawable.cell_beige : R.drawable.cell_white, null));
        setGravity(Gravity.CENTER);
        setMaxLines(1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int size = Math.min(width, height) / boardSize.getSize();
        setMeasuredDimension(size, size);
    }
}
