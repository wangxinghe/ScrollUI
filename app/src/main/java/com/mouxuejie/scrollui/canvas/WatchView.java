package com.mouxuejie.scrollui.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wangxinghe1 on 2016/11/14.
 */

public class WatchView extends View {
    private static final int DEFAULT_SIZE = 300;

    private Paint mPaint;
    private Paint mLinePaint;

    public WatchView(Context context) {
        super(context);
        initPaint();
    }

    public WatchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public WatchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(DEFAULT_SIZE, DEFAULT_SIZE);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(DEFAULT_SIZE, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, DEFAULT_SIZE);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        int radius = Math.min(width, height) / 2;

        canvas.drawCircle(width / 2, height / 2, radius, mPaint);

        canvas.drawLine(width / 2, height / 2 - radius / 2, width / 2, height / 2 - radius, mLinePaint);

        canvas.save();

        for (int i = 0; i < 11; i++) {
            canvas.rotate(30, width / 2, height / 2);
            canvas.drawLine(width / 2, height / 2 - radius / 2, width / 2, height / 2 - radius, mLinePaint);
        }

        canvas.restore();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);

        mLinePaint = new Paint();
        mLinePaint.setColor(Color.BLUE);
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStyle(Paint.Style.FILL);
    }
}
