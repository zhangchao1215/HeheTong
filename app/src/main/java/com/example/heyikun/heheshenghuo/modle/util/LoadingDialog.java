package com.example.heyikun.heheshenghuo.modle.util;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.heyikun.heheshenghuo.R;


/**
 * 居中dialog基类
 * Created by echo on 15/6/20.
 */
public class LoadingDialog extends Dialog {
    private static int default_width = 160; //默认宽度
    private static int default_height = 120;//默认高度

    public LoadingDialog(Context mParentActivity) {
        this(mParentActivity, default_width, default_height, R.layout.dialog_loading, R.style.Theme_dialog);
    }

    public LoadingDialog(Context mParentActivity, int layout){
        this(mParentActivity, default_width, default_height, layout, R.style.Theme_dialog);
    }

    public LoadingDialog(Context mParentActivity, int layout, int style) {
        this(mParentActivity, default_width, default_height, layout, style);
    }

    public LoadingDialog(Context mParentActivity, int width, int height, int layout, int style) {
        super(mParentActivity, style);
//set content
        setContentView(layout);
//set window params
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
//set width,height by density and gravity
        float density = getDensity(mParentActivity);
        params.width = (int) (width * density);
        params.height = (int) (height * density);
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);

        this.setCanceledOnTouchOutside(false);
    }

    @Override
    public void cancel() {
        super.cancel();    //调用超类对应方法
    }

    private float getDensity(Context context) {
        Resources resources = context.getResources() ;
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.density;
    }
}
