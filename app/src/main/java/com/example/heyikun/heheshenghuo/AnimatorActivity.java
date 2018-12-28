package com.example.heyikun.heheshenghuo;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.widget.ImageView;

import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by hyk on 2017/9/13.
 */

public class AnimatorActivity extends BaseActivity {
    @BindView(R.id.DongHua_SaoMiao)
    ImageView DongHuaSaoMiao;

    @Override
    protected int layoutId() {
        return R.layout.donghua;
    }

    @Override
    protected void initView() {
        float translationY = DongHuaSaoMiao.getTranslationY();

        ObjectAnimator animator = ObjectAnimator.ofFloat(DongHuaSaoMiao, "translationY", translationY, 300f, translationY);
        animator.setDuration(4000).setRepeatCount(ValueAnimator.INFINITE);
         animator.setRepeatMode(ValueAnimator.RESTART);
         animator.start();

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {

    }


}
