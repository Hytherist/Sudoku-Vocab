package com.foxtrot.sudoku.view;

import android.content.Context;

import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.widget.TextViewCompat;
import com.foxtrot.sudoku.R;
import com.foxtrot.sudoku.model.BoardSize;
import com.foxtrot.sudoku.model.Pair;

public class SudokuCellView extends AppCompatTextView {

    private final BoardSize boardSize;

    private final int row;

    private final int col;

    private final boolean clickable;

    private final Pair<String, String> wordPair;

    public SudokuCellView(@NonNull Context context, BoardSize boardSize, int row, int col, boolean clickable, Pair<String, String> wordPair) {
        super(context);
        this.boardSize = boardSize;
        this.row = row;
        this.col = col;
        this.clickable = clickable;
        this.wordPair = wordPair;

        initializeCell();
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
        setText(wordPair == null ? "" : wordPair.getSecond());

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
