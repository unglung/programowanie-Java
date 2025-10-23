package com.example.chessboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowInsets;

import static java.lang.Math.min;

public class ChessBoardView extends View {

    private Paint black = new Paint();
    private Paint white = new Paint();
    private Paint red = new Paint();
    private int[] colors = new int[64];
    private int topOffset = 0, bottomOffset = 0;

    public ChessBoardView(Context context) { super(context); init(); }
    public ChessBoardView(Context context, AttributeSet attrs) { super(context, attrs); init(); }

    private void init() {
        black.setColor(Color.BLACK);
        white.setColor(Color.WHITE);
        red.setColor(Color.RED);
        // ustawienie początkowych kolorów szachownicy
        for (int i = 0; i < 64; i++) {
            colors[i] = ((i / 8 + i % 8) % 2 == 0) ? Color.WHITE : Color.BLACK;
        }
    }

    @Override
    public WindowInsets onApplyWindowInsets(WindowInsets insets) {
        topOffset = insets.getSystemWindowInsetTop();
        bottomOffset = insets.getSystemWindowInsetBottom();
        return super.onApplyWindowInsets(insets);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int w = getWidth();
        int h = getHeight();
        int size = min(w, h - topOffset - bottomOffset);
        int offsetX = (w - size) / 2;
        int offsetY = topOffset + (h - topOffset - bottomOffset - size) / 2;

        int cellSize = size / 8;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                int idx = row * 8 + col;
                Paint paint;
                if (colors[idx] == Color.BLACK) paint = black;
                else if (colors[idx] == Color.RED) paint = red;
                else paint = white;

                canvas.drawRect(
                        offsetX + col * cellSize,
                        offsetY + row * cellSize,
                        offsetX + (col + 1) * cellSize,
                        offsetY + (row + 1) * cellSize,
                        paint
                );
            }
        }
    }

    public void changeColors() {
        for (int i = 0; i < 64; i++) {
            if (colors[i] == Color.BLACK) colors[i] = Color.RED;
        }
        invalidate();
    }

    public void resetColors() {
        for (int i = 0; i < 64; i++) {
            colors[i] = ((i / 8 + i % 8) % 2 == 0) ? Color.WHITE : Color.BLACK;
        }
        invalidate();
    }

    public int[] getColors() { return colors.clone(); }

    public void setColors(int[] c) {
        if (c != null && c.length == 64) {
            System.arraycopy(c, 0, colors, 0, 64);
            invalidate();
        }
    }
}
