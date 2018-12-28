package com.example.heyikun.heheshenghuo.modle.util;

import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

/**
 * Created by hyk on 2018/1/16.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2018/1/16
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class DownPopupWindow extends PopupWindow {

    @Override
    public void showAsDropDown(View anchorView, int xoff, int yoff) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) {
            int[] a = new int[2];
            anchorView.getLocationInWindow(a);
            showAtLocation(anchorView, Gravity.NO_GRAVITY, xoff, a[1] + anchorView.getHeight() + yoff);
        } else {
            super.showAsDropDown(anchorView, xoff, yoff);
        }
    }

    @Override
    public void showAsDropDown(View anchorView) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) {
            int[] a = new int[2];
            anchorView.getLocationInWindow(a);
            showAtLocation(anchorView, Gravity.NO_GRAVITY, 0, a[1] + anchorView.getHeight() + 0);
        } else {
            super.showAsDropDown(anchorView);
        }
    }
}
