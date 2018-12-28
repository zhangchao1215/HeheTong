package com.example.heyikun.heheshenghuo.modle.util;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heyikun.heheshenghuo.R;

/**
 * Created by hyk on 2017/11/16.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/11/16
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能： EmptyView
 */

public class CustomRelativeLayout extends RelativeLayout {

    public static interface RetryListener {
        void retry();
    }

    private ProgressBar progressBar = null;
    private Button btn_refresh = null;
    private TextView tv_tip = null;
    private boolean isNormal = true;
    private RetryListener retryListener = null;

    private final static int tvTipId = 0x1001;


    public CustomRelativeLayout(Context context) {
        this(context, null);
    }


    public CustomRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomRelativeLayout(Context context, AttributeSet attrs,
                                int defStyle) {
        super(context, attrs, defStyle);
    }


    /**
     * 设置加载界面
     */
    public void setInProgress() {
        if (progressBar == null) {
            int size = (int) TypedValue
                    .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 45, getResources().getDisplayMetrics());
            progressBar = new ProgressBar(getContext());
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(size, size);
            lp.addRule(RelativeLayout.CENTER_IN_PARENT);
            progressBar.setLayoutParams(lp);
            addView(progressBar);
        }

        if (getChildCount() > 0) {
            int childCount = getChildCount();
            if (childCount > 0) {
                for (int i = (childCount - 1); i >= 0; i--) {
                    getChildAt(i).setVisibility(View.GONE);
                }
            }
        }

        if (btn_refresh != null)
            btn_refresh.setVisibility(View.GONE);
        if (tv_tip != null)
            tv_tip.setVisibility(View.GONE);

        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * 显示加载失败界面,隐藏所有正常的View元素
     */
    public void setChildrenGone() {
        if (btn_refresh == null) {
            btn_refresh = new Button(getContext());
            tv_tip = new TextView(getContext());
            tv_tip.setText("正在加载数据");
            tv_tip.setGravity(Gravity.CENTER);
            tv_tip.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            btn_refresh.setText("刷新");

            btn_refresh.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (retryListener != null) {
                        retryListener.retry();
                    } else {
                        Toast.makeText(getContext(), "暂无数据", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            int btnWidth = (int) TypedValue
                    .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics());
            RelativeLayout.LayoutParams lp
                    = new RelativeLayout.LayoutParams(btnWidth, LayoutParams.WRAP_CONTENT);
            lp.addRule(RelativeLayout.CENTER_IN_PARENT);
            lp.setMargins(0, 10, 0, 0);
            btn_refresh.setLayoutParams(lp);
            btn_refresh.setId(tvTipId);
            btn_refresh.setVisibility(View.GONE);
            addView(btn_refresh);

            RelativeLayout.LayoutParams lp_tv
                    = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            lp_tv.addRule(RelativeLayout.ABOVE, tvTipId);
            tv_tip.setLayoutParams(lp_tv);
            addView(tv_tip);
        }

        if (getChildCount() > 0) {
            int childCount = getChildCount();
            if (childCount > 0) {
                for (int i = (childCount - 1); i >= 0; i--) {
                    getChildAt(i).setVisibility(View.GONE);
                }
            }
        }

        if (progressBar != null)
            progressBar.setVisibility(View.GONE);

        isNormal = false;
        btn_refresh.setVisibility(View.VISIBLE);
        tv_tip.setVisibility(View.VISIBLE);
    }

    /**
     * 显示正常View元素
     */
    public void setChildrenVisible() {
        if (getChildCount() > 0) {
            int childCount = getChildCount();
            if (childCount > 0) {
                for (int i = (childCount - 1); i >= 0; i--) {
                    getChildAt(i).setVisibility(View.VISIBLE);
                }
            }
            if (progressBar != null)
                progressBar.setVisibility(View.GONE);

            if (btn_refresh != null)
                btn_refresh.setVisibility(View.GONE);

            if (tv_tip != null)
                tv_tip.setVisibility(View.GONE);

            isNormal = true;
        }
    }

    public boolean isNormalView() {
        return isNormal;
    }

    public void setRetryListener(RetryListener listener) {
        this.retryListener = listener;
    }


}







