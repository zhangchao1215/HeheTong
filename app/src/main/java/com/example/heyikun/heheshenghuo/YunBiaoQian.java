package com.example.heyikun.heheshenghuo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.lujun.androidtagview.ColorFactory;
import co.lujun.androidtagview.TagContainerLayout;

/**
 * Created by hyk on 2017/9/13.
 */

public class YunBiaoQian extends Activity {


    @BindView(R.id.YunBiao)
    TagContainerLayout YunBiao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yunbiaoqian);
        ButterKnife.bind(this);
        YunBiao.setTheme(ColorFactory.NONE);
        YunBiao.setTagBackgroundColor(Color.TRANSPARENT);
        YunBiao.addTag("吃药");
        YunBiao.addTag("睡觉");
        YunBiao.addTag("打豆豆");

    }
}
