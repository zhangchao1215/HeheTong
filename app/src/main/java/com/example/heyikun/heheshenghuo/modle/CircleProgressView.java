package com.example.heyikun.heheshenghuo.modle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.heyikun.heheshenghuo.R;

/**
 * Created by hyk on 2018/1/13.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2018/1/13
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class CircleProgressView extends View {


    private static final String TAG = "CircleProgressView";

    //设置进度
    private int mMaxProgress = 100;  //最大值
    private int mProgress = 30;     //当前值

    //设置画笔的宽度
    private final int mCircleLineStrokeWidthBottom = 1;
    private final int mCircleLineStrokeWidthAbove = 8;

    // 画圆所在的距形区域
    private final RectF mRectFBottom;
    private final RectF mRectFAbove;

    private final Paint mPaint;

    private final Context mContext;
    private int width;
    private int height;
    private int minColor;
    private int MaxColor;
    private boolean isOddNumber= false; //是否是奇数阶段

    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        mRectFBottom = new RectF();
        mRectFAbove = new RectF();
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = this.getWidth();
        height = this.getHeight();

        if (width != height) {
            int min = Math.min(width, height);
            width = min;
            height = min;
        }

        // 设置背景画笔相关属性
        mPaint.setAntiAlias(true);  //抗锯齿
        mPaint.setColor(getResources().getColor(R.color.colorTextYangXIn));
        canvas.drawColor(Color.TRANSPARENT); //设置背景透明
        mPaint.setStrokeWidth(mCircleLineStrokeWidthBottom);
        mPaint.setStyle(Paint.Style.STROKE);
        // 位置
        setRectFPosition(mRectFBottom, mCircleLineStrokeWidthBottom);
        setRectFPosition(mRectFAbove, mCircleLineStrokeWidthAbove);

        // 绘制圆圈，进度条背景
        canvas.drawArc(mRectFBottom, -90, 360, false, mPaint); //顺时针为正，起始点-90是视图上最高的点

        //绘制上面可变的进度条
        mPaint.setColor(MaxColor);
        mPaint.setStrokeWidth(mCircleLineStrokeWidthAbove);

        //        if (isOddNumber) { //奇数轮时
        //            canvas.drawArc(mRectFAbove, -90, ((float) mProgress / mMaxProgress) * 360, false, mPaint);
        //        } else {           //偶数轮时，反方向绘制
        //        }
        canvas.drawArc(mRectFAbove, 180, ((float) mProgress / mMaxProgress) * 360, false, mPaint);

    }

    /**
     * 设置RectF位置
     * @param mRectF
     * @param mCircleLineStrokeWidth
     */
    private void setRectFPosition(RectF mRectF, int mCircleLineStrokeWidth) {
        mRectF.left = mCircleLineStrokeWidth / 2; // 左上角x
        mRectF.top = mCircleLineStrokeWidth / 2; // 左上角y
        mRectF.right = width - mCircleLineStrokeWidth / 2; // 左下角x
        mRectF.bottom = height - mCircleLineStrokeWidth / 2; // 右下角y
    }

    public int getMaxProgress() {
        return mMaxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        this.mMaxProgress = maxProgress;
    }

    public void setProgress(int progress) {
        this.mProgress = progress;
        this.invalidate();
    }

    public void setProgressNotInUiThread(int progress) {
        this.mProgress = progress;
        this.postInvalidate();
    }

    public void setMinColor(int minColor) {
        this.minColor = minColor;
    }

    public void setMaxColor(int maxColor) {
        MaxColor = maxColor;
    }
}
