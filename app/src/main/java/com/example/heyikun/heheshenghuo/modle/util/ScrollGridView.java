package com.example.heyikun.heheshenghuo.modle.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ListView;

/**
 * what's this?
 * Created by YC on 2017/9/23
 */

public class ScrollGridView extends GridView {
    public ScrollGridView(Context context) {
        this(context, null);
    }

    public ScrollGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // 记录上一次滑动的位置
    private int mLastX = 0;
    private int mLastY = 0;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                // 禁止父组件拦截当前事件
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                if (Math.abs(deltaX) > Math.abs(deltaY)){
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                this.setOnScrollListener(new OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(AbsListView view, int scrollState) {

                    }

                    @Override
                    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                        int last = view.getLastVisiblePosition();
                        if (last == (totalItemCount - 1)){
                            getParent().requestDisallowInterceptTouchEvent(true);
                        }
                    }
                });
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        mLastX = x;
        mLastY = y;
        return super.dispatchTouchEvent(ev);
    }
}
