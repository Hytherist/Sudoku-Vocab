package com.foxtrot.sudoku.view;

import android.content.Context;

import androidx.appcompat.widget.AppCompatTextView;

public class SudokuCellView extends AppCompatTextView {

    private final int gridSize;

    public SudokuCellView(Context context) {
        super(context);
        gridSize = 0;
    }

    public SudokuCellView(Context context, int gridSize) {
        super(context);
        this.gridSize = gridSize;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int size = Math.min(width, height) / gridSize;
        setMeasuredDimension(size, size);
    }
}
