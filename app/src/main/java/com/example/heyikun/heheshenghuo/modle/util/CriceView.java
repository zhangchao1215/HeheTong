package com.example.heyikun.heheshenghuo.modle.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by localadmin on 2018/1/14.
 */

public class CriceView extends View {
    private Paint mPaint;

    private static final int MOST_PADDING = 5;
    private float criceRadios;

    private int criceColor;

    private int stokeColor;

    private float criceWidth;

    private float stokeWidth;
    private float position;

    /**
     * 0-100
     * @param position
     */
    public void setPosition(int position) {
        this.position = position;
    }

    public void setCriceRadios(float criceRadios) {
        this.criceRadios = criceRadios;
    }

    public void setCriceColor(int criceColor) {
        this.criceColor = criceColor;
    }

    public void setStokeColor(int stokeColor) {
        this.stokeColor = stokeColor;
    }

    public void setCriceWidth(float criceWidth) {
        this.criceWidth = criceWidth;
    }

    public void setStokeWidth(float stokeWidth) {
        this.stokeWidth = stokeWidth;
    }

    public CriceView(Context context) {
        super(context);
    }

    public CriceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CriceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        setPadding(10,10,10,10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(criceColor);
        mPaint.setStrokeWidth(criceWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        RectF rectF = new RectF(MOST_PADDING * 3, MOST_PADDING * 3, criceRadios * 2, criceRadios * 2);
//        float radiowidth = criceRadios * 2;
        canvas.drawCircle(criceRadios + MOST_PADDING, criceRadios + MOST_PADDING, criceRadios - MOST_PADDING * 2, mPaint);

        mPaint.setColor(stokeColor);
        mPaint.setStrokeWidth(stokeWidth);
        final float v = position / 100;
        canvas.drawArc(rectF, 180, v*360, false, mPaint);

//        canvas.drawText();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int widthModle = MeasureSpec.getMode(widthMeasureSpec);
        final int heightModle = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        switch (widthModle) {
            case MeasureSpec.EXACTLY:
                criceRadios = ((width) / 2);
                break;
            case MeasureSpec.AT_MOST:
                if ((criceRadios * 2) > width) {
                    criceRadios = width / 2;
                } else {
                    width = (int) (criceRadios * 2);
                }
                break;
            default:
        }
        setMeasuredDimension(width, width);

    }
}
