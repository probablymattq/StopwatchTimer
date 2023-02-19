package com.matter.stopwatchtimer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class RoundProgressBar extends View {
    private int progress = 0;
    private int max = 100;
    private final int strokeWidth = 10;
    private Paint backgroundPaint;
    private Paint progressPaint;

    public RoundProgressBar(Context context) {
        super(context);
        init();
    }

    public RoundProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        backgroundPaint = new Paint();
        int backgroundColor = Color.GRAY;
        backgroundPaint.setColor(backgroundColor);
        backgroundPaint.setStyle(Paint.Style.STROKE);
        backgroundPaint.setStrokeWidth(strokeWidth);

        progressPaint = new Paint();
        int progressColor = Color.parseColor("#ffea00");
        progressPaint.setColor(progressColor);
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeWidth(strokeWidth);
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    public int getProgress(){
        return progress;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        int radius = Math.min(width, height) / 2 - strokeWidth / 2;

        canvas.drawCircle(width / 2, height / 2, radius, backgroundPaint);

        RectF rectF = new RectF(width / 2 - radius, height / 2 - radius, width / 2 + radius, height / 2 + radius);
        canvas.drawArc(rectF, -90, 360 * progress / max, false, progressPaint);
    }

    public int getMax() {
        return max;
    }
}

