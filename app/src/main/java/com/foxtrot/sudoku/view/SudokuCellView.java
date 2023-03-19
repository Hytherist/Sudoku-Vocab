package com.foxtrot.sudoku.view;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class SudokuCellView extends AppCompatTextView {

    private final int boardSize;

    public SudokuCellView(@NonNull Context context, int boardSize) {
        super(context);
        this.boardSize = boardSize;
    }

    public SudokuCellView(@NonNull Context context, @Nullable AttributeSet attrs, int boardSize) {
        super(context, attrs);
        this.boardSize = boardSize;
    }

    public SudokuCellView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int boardSize) {
        super(context, attrs, defStyleAttr);
        this.boardSize = boardSize;
    }

    private void initializeCell() {}

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int size = Math.min(width, height) / boardSize;
        setMeasuredDimension(size, size);
    }
}
