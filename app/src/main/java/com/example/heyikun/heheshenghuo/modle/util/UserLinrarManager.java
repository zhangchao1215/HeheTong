package com.example.heyikun.heheshenghuo.modle.util;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by hyk on 2017/12/11.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/12/11
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class UserLinrarManager extends LinearLayoutManager {
    public UserLinrarManager(Context context) {
        super(context);
    }

    public UserLinrarManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public UserLinrarManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}
